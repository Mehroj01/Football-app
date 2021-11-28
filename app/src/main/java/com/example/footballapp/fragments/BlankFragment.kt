package com.example.footballapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.footballapp.R
import com.example.footballapp.databinding.FragmentBlankBinding
import com.example.footballapp.viewpager.VBlankAdapter
import com.google.android.material.tabs.TabLayoutMediator

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BlankFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var _binding: FragmentBlankBinding? = null
    private lateinit var vBlankAdapter: VBlankAdapter
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlankBinding.inflate(inflater, container, false)
        vBlankAdapter = VBlankAdapter(parentFragmentManager, lifecycle)
        binding.apply {
            viewpager.adapter = vBlankAdapter
            TabLayoutMediator(tab, viewpager) { tab, pos ->
                when (pos) {
                    0 -> {
                        tab.text = "All"
                    }
                    1 -> {
                        tab.text = "Home"
                    }
                    2 -> {
                        tab.text = "Away"
                    }
                }
            }.attach()
        }
        return binding.root
    }


}