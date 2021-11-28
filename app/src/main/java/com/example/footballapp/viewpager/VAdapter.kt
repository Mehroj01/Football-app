package com.example.footballapp.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.footballapp.R
import com.example.footballapp.fragments.BlankFragment


class VAdapter(fg: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fg, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BlankFragment()
            1 -> MatchesFragment()
            else -> {
                BlankFragment()
            }
        }

    }
}

class MatchesFragment : Fragment(R.layout.fragment_matches) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
