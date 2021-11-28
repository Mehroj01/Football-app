package com.example.footballapp.models

data class Team(
    var abbreviation: String,
    var displayName: String,
    var id: String,
    var isActive: Boolean,
    var location: String,
    var logos: List<Logo>,
    var name: String,
    var shortDisplayName: String,
    var uid: String
)