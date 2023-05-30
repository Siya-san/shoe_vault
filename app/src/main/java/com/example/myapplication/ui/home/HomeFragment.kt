package com.example.myapplication.ui.home

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentCategoryBinding
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.category.Category
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var database: DatabaseReference
    private lateinit var categoryRecyclerview : RecyclerView
    private lateinit var categoryArrayList : ArrayList<Category>
    private lateinit var mLinearLayoutManager: LinearLayoutManager
    private var tempArray =  mutableListOf<String>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(/* inflater = */ inflater, /* parent = */
            container, /* attachToParent = */
            false)
        val root: View = binding.root

        val rView: RecyclerView =binding.categoryList
        categoryRecyclerview = rView
        categoryRecyclerview.layoutManager = LinearLayoutManager(context)
        categoryRecyclerview.setHasFixedSize(true)
        categoryArrayList = arrayListOf<Category>()
        database = FirebaseDatabase.getInstance().getReference("Categories")
        database.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    categoryArrayList.clear()
                    tempArray.clear()
                    var category: String?=null
                    for (categorySnapshot in snapshot.children){
                        val categoryMap = categorySnapshot.value as? HashMap<*, *>
                        categoryMap?.let {
                            val category = Category(
                                categoryMap["category_name"] as? String,
                                categoryMap["category_description"] as? String,
                                categoryMap["category_size"] as? String
                            )
                            category?.let {
                                categoryArrayList.add(category)
                            }
                        }
                    }

                    categoryRecyclerview.adapter?.notifyDataSetChanged()
                }else{
                    Log.d("HomeFragment", "Failed load")
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        categoryRecyclerview.adapter = HomeAdaptor(categoryArrayList)
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun getCategoryData() {



    }
}


