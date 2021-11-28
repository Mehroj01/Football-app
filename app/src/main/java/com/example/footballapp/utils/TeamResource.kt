package com.example.footballapp.utils

import com.example.footballapp.models.Teams
import com.example.footballapp.room.Team

sealed class TeamResource {
    object Loading : TeamResource()
    data class Error(var message: String) : TeamResource()
    data class Success(var body: Teams) : TeamResource()
}