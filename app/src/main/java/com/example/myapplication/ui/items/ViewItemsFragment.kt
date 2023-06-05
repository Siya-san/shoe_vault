package com.example.myapplication.ui.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentViewItemsBinding


class ViewItemsFragment : Fragment() {
    private lateinit var binding: FragmentViewItemsBinding
    private var position: Int = 0

    companion object {
        private const val ARG_POSITION = "arg_position"

        fun newInstance(position: Int): AddItemFragment {
            val fragment = AddItemFragment()
            val args = Bundle()
            args.putInt(ARG_POSITION, position)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_items, container, false)
    }


}