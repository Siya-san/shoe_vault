package com.example.myapplication.ui.category


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentCategoryBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore


class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private lateinit var firestore: FirebaseFirestore
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {


        _binding = FragmentCategoryBinding.inflate(/* inflater = */ inflater, /* parent = */
            container, /* attachToParent = */
            false)
        val root: View = binding.root
        firestore = FirebaseFirestore.getInstance()
        binding.addCategory.setOnClickListener{
            var tempname =binding.name.text.toString()
            val tempdescprition =binding.description.text.toString()
            val tempsize =binding.size.text.toString()
            if (tempname.endsWith(" ")) {
                tempname.dropLast(1)
            }


            val category = Category(tempname,tempdescprition,tempsize)
              firestore.collection("categories")
                .document(tempname)
                .set(category)
                .addOnSuccessListener {
                    binding.name.text?.clear()
                    binding.description.text?.clear()
                    binding.size.text?.clear()
                    Log.d("CategoryFragment", "Saved")
                }
                .addOnFailureListener {
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