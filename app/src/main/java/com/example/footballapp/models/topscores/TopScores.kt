package com.example.playfootballmvvm.model.topscores

import com.example.footballapp.models.topscores.Competition

data class TopScores(
    val competition: Competition,
    val count: Int,
    val filters: Filters,
    val scorers: List<Scorer>,
    val season: Season
)