package com.example.footballapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballapp.respository.TeamRepository
import com.example.footballapp.utils.LeagueResource
import com.example.footballapp.utils.MainModelResource
import com.example.footballapp.utils.NetworkHelper
import com.example.footballapp.utils.TeamResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val teamRepository: TeamRepository,
    private val networkHelper: NetworkHelper
) :
    ViewModel() {

    fun fetchLeagues(): MutableStateFlow<LeagueResource> {
        val stateFlow = MutableStateFlow<LeagueResource>(LeagueResource.Loading)
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                teamRepository.getLeagues().catch {
                    stateFlow.emit(LeagueResource.Error("${it.message} \n ${it.cause}"))
                }.collect {
                    if (it.isSuccessful) {
                        stateFlow.emit(LeagueResource.Success(it.body()!!))
                    }
                }
            }

        }
        return stateFlow

    }

    fun fetchTeamsByLeague(id: String): MutableStateFlow<TeamResource> {
        val stateFlow = MutableStateFlow<TeamResource>(TeamResource.Loading)
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                teamRepository.getTeamsByLeague(id).catch {
                    stateFlow.emit(TeamResource.Error("${it.message} \n ${it.cause}"))
                }.collect {
                    if (it.isSuccessful) {
                        stateFlow.emit(TeamResource.Success(it.body()!!))
                    }
                }
            }

        }
        return stateFlow
    }

    fun fetchMainModel(): MutableStateFlow<MainModelResource> {
        val stateFlow = MutableStateFlow<MainModelResource>(MainModelResource.Loading)
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                teamRepository.getMainModel().catch {
                    stateFlow.emit(MainModelResource.Error("${it.message} \n ${it.cause}"))
                }.collect {
                    if (it.isSuccessful) {
                        stateFlow.emit(MainModelResource.Success(it.body()))
                    }
                }
            }

        }
        return stateFlow
    }
}