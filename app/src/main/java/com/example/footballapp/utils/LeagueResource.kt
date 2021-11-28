package com.example.footballapp.utils

import com.example.footballapp.models.League

sealed class LeagueResource {
    object Loading : LeagueResource()
    data class Error(var message: String) : LeagueResource()
    data class Success(var leagues: League) : LeagueResource()
}