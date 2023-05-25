package com.example.myapplication.ui.category


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentCategoryBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private lateinit var database: DatabaseReference
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val categoryViewModel =
            ViewModelProvider(this).get(CategoryViewModel::class.java)

        _binding = FragmentCategoryBinding.inflate(/* inflater = */ inflater, /* parent = */
            container, /* attachToParent = */
            false)
        val root: View = binding.root
        binding.addCategory.setOnClickListener{
            val tempname =binding.name.text.toString()
            val tempdescprition =binding.description.text.toString()
            val tempsize =binding.size.text.toString()
            database = FirebaseDatabase.getInstance().getReference("Category")
            val category = Category(tempname,tempdescprition,tempsize)
            database.child(tempname).setValue(category).addOnSuccessListener {
                binding.name.text?.clear()
                binding.description.text?.clear()
                binding.size.text?.clear()

                Log.d("CategoryFragment", "Saved")

            }.addOnFailureListener{

                Log.d("CategoryFragment", "Failed")
            }

        }
        return root

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}