package com.example.myapplication.ui.items

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentAddItemBinding
import com.example.myapplication.databinding.FragmentViewItemsBinding

class AddItemFragment : Fragment() {

    private lateinit var binding: FragmentAddItemBinding
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
        arguments?.let {
            position = it.getInt(ARG_POSITION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddItemBinding.inflate(inflater, container, false)
        return binding.root
    }
}
