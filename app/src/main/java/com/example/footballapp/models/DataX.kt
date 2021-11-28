package com.example.footballapp.models

data class DataX(
    var abbreviation: String,
    var name: String,
    var season: Int,
    var seasonDisplay: String,
    var standings: List<Standing>
)