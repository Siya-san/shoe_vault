package com.example.myapplication.ui.items

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ItemPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        // Return the number of tabs/items
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        // Return the fragment for each tab
        // Replace `YourFragment` with your actual fragment class
        return AddItemFragment.newInstance(position)
    }
}