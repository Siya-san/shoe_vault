package com.example.myapplication.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.category.Category

class HomeAdaptor(private val categoryList : ArrayList<Category>,private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<HomeAdaptor.MyViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(category: Category)
    }
    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_fields,
            parent,false)
        return MyViewHolder(itemView)

    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = categoryList[position]

        holder.name.text = currentitem.category_name
        holder.description.text = currentitem.category_description
        holder.size.text = currentitem.category_size
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(currentitem)
        }

    }
    override fun getItemCount(): Int {

        return categoryList.size
    }
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val name : TextView = itemView.findViewById(R.id.categoryName)
        val description : TextView = itemView.findViewById(R.id.categoryDescription)
        val size : TextView = itemView.findViewById(R.id.categorySize)

    }


}