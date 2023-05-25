package com.example.myapplication.ui.category

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CategoryViewModel : ViewModel() {

    private val nameArray = mutableListOf<String>()



    private val descriptionArray = mutableListOf<String>()
    private val sizeArray = mutableListOf<String>()
    private val _text = MutableLiveData<String>().apply {
        value = "This is Category Fragment"
    }
    val text: LiveData<String> = _text
fun addCategory(name:String, description:String, size:String){
    val category = hashMapOf(
        "category_Name" to name,
        "category_Description" to description,
        "category_Size" to size.toInt(),
    )

  //db.collection("categories").document("Cat")
  //    .set(category)
  //    .addOnSuccessListener {

  //        Log.d("Tag", "DocumentSnapshot successfully written!")
  //    }
  //    .addOnFailureListener { e -> Log.w("Tag", "Error writing document", e) }
    nameArray.add(name)
    descriptionArray.add(description)
    sizeArray.add(size)
    Log.d("ViewCategories", nameArray[0] + descriptionArray[0] + sizeArray[0] +"  VIEW")
}
fun getCategories() :MutableList<String>{
   return nameArray
}
}