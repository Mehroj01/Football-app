package com.example.footballapp.respository

import com.example.footballapp.retrofit.ApiService
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET

class TeamRepository(var apiSer: ApiService) {
    suspend fun getLeagues() = flow { emit(apiSer.getLeagues()) }
    suspend fun getTeamsByLeague(id: String) = flow { emit(apiSer.getTeamsByLeague(id)) }
    suspend fun getMainModel() = flow { emit(apiSer.getMainModel()) }


}