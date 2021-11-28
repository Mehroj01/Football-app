package com.example.footballapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.footballapp.respository.TeamRepository
import com.example.footballapp.utils.NetworkHelper

class ViewModelFactory(private val teamRepository: TeamRepository, private val networkHelper: NetworkHelper) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(teamRepository, networkHelper) as T
        }
        throw Exception("Error")
    }
}