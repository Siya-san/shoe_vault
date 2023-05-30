package com.example.myapplication.ui.search


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.ui.home.HomeViewModel

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private lateinit var database : DatabaseReference
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val searchViewModel =
            ViewModelProvider(this)[SearchViewModel::class.java]


        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.searchButton.setOnClickListener {
            val searchPhone: String = binding.searchPhone.text.toString()
            if (searchPhone.isNotEmpty()) {
               readData(searchPhone)
            } else {
                Log.d("SearchFragment", "Result")
            }
        }


        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun readData(name: String) {
        database = FirebaseDatabase.getInstance().getReference("Categories")
        database.child(name).get().addOnSuccessListener {
            if (it.exists()){

                val description = it.child("category_description").value
                val size = it.child("category_size").value
                Log.d("SearchFragment", "Results Found")

                binding.searchPhone.text.clear()

                binding.name.text= name
                binding.description.text = description.toString()
                binding.size.text = size.toString()
            }else{
                Log.d("SearchFragment", "Phone number does not exist")

            }
        }.addOnFailureListener{
            Log.d("SearchFragment", "Something went wrong")

        }
    }
}








