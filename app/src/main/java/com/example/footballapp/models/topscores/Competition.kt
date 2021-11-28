package com.example.footballapp.models.topscores

import com.example.playfootballmvvm.model.topscores.Area

data class Competition(
    val area: Area,
    val code: String,
    val id: Int,
    val lastUpdated: String,
    val name: String,
    val plan: String
)