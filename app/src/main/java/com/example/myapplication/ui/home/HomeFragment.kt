package com.example.myapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.category.Category
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot


class HomeFragment : Fragment(),HomeAdaptor.OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var firestore: FirebaseFirestore
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
        firestore = FirebaseFirestore.getInstance()
        val collectionRef = firestore.collection("categories")
        collectionRef.addSnapshotListener { snapshot: QuerySnapshot?, error: Exception? ->
            if (error != null) {
                Log.e("HomeFragment", "Error fetching categories", error)
                return@addSnapshotListener
            }

            snapshot?.let { snapshot ->
                categoryArrayList.clear()
                tempArray.clear()

                for (document in snapshot.documents) {
                    val category = document.toObject(Category::class.java)
                    category?.let {
                        categoryArrayList.add(category)
                    }
                }

                categoryRecyclerview.adapter?.notifyDataSetChanged()
            }
        }

        categoryRecyclerview.adapter = HomeAdaptor(categoryArrayList, this)
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onItemClick(category: Category) {
        val mainActivity = activity as MainActivity
        mainActivity.onItemClick(category)
    }


}


