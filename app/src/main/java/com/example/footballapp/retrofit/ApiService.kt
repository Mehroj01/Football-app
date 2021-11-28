package com.example.footballapp.retrofit

import com.example.footballapp.model_extra.MainModel
import com.example.footballapp.models.League
import com.example.footballapp.models.Teams
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("leagues")
    suspend fun getLeagues(
    ): Response<League>

    @GET("leagues/{id}/standings?season=2021")
    suspend fun getTeamsByLeague(@Path("id") id: String): Response<Teams>


    @Headers("X-Auth-Token: 86b23b1411c6428da8568d8ababdc25c")
    @GET
    suspend fun getMainModel(@Url url: String = "https://api.football-data.org/v2/competitions/2021/standings?standingType=HOME"): Response<MainModel>

}