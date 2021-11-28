package com.example.footballapp.model_extra

data class MainModel(
    var competition: Competition,
    var filters: Filters,
    var season: Season,
    var standings: List<Standing>
)