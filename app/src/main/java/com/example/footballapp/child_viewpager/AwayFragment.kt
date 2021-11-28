package com.example.footballapp.child_viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.footballapp.R
import com.example.footballapp.adapters.AwayListViewAdapter
import com.example.footballapp.adapters.ListAdapter
import com.example.footballapp.databinding.FragmentAwayBinding
import com.example.footballapp.respository.Extra
import com.example.footballapp.respository.TeamRepository
import com.example.footballapp.retrofit.ApiClient
import com.example.footballapp.utils.NetworkHelper
import com.example.footballapp.utils.TeamResource
import com.example.footballapp.viewmodels.MainViewModel
import com.example.footballapp.viewmodels.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AwayFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var _binding: FragmentAwayBinding? = null
    private lateinit var mainViewModel: MainViewModel
    private lateinit var listAdapter: AwayListViewAdapter
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAwayBinding.inflate(inflater, container, false)
        mainViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(
                TeamRepository(ApiClient.apiService),
                networkHelper = NetworkHelper(requireContext())
            )
        )[MainViewModel::class.java]
        lifecycleScope.launch {
            mainViewModel.fetchTeamsByLeague(Extra.getLeague1()!!.id).collect {
                when (it) {
                    is TeamResource.Success -> {
                        listAdapter = AwayListViewAdapter(requireContext(), it.body)
                        binding.listView.adapter = listAdapter
                    }
                }
            }
        }


        return binding.root
    }


}