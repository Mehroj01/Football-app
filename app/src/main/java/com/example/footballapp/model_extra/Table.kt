package com.example.footballapp.model_extra

data class Table(
    var draw: Int,
    var form: Any,
    var goalDifference: Int,
    var goalsAgainst: Int,
    var goalsFor: Int,
    var lost: Int,
    var playedGames: Int,
    var points: Int,
    var position: Int,
    var team: Team,
    var won: Int
)