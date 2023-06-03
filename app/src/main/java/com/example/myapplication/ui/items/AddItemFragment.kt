package com.example.myapplication.ui.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentItemBinding
import com.example.myapplication.ui.category.Category
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AddItemFragment : Fragment() {

    private lateinit var category: Category
    private lateinit var binding: FragmentItemBinding
    companion object {
        private const val ARG_CATEGORY = "arg_category"

        fun newInstance(category: Category): AddItemFragment {
            val fragment = AddItemFragment()
            val args = Bundle()
            args.putParcelable(ARG_CATEGORY, category)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getParcelable(ARG_CATEGORY)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentItemBinding.inflate(inflater, container, false)

        binding.itemNameTextView.text = category.category_name
        binding.itemDescriptionTextView.text = category.category_description
        binding.itemSizeTextView.text = category.category_size

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavView = requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        val navController = findNavController()
        bottomNavView.setupWithNavController(navController)

    }


}
