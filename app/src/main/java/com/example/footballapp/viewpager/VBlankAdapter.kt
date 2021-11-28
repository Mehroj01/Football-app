package com.example.footballapp.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.footballapp.child_viewpager.AllFragment
import com.example.footballapp.child_viewpager.AwayFragment
import com.example.footballapp.fragments.BlankFragment
import com.example.footballapp.fragments.HomePlFragment


class VBlankAdapter(fg: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fg, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllFragment()
            1 -> HomePlFragment()
            2 -> AwayFragment()
            else -> {
                AllFragment()
            }
        }

    }

}