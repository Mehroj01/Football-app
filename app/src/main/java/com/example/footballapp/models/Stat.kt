package com.example.footballapp.models

data class Stat(
    var abbreviation: String,
    var description: String,
    var displayName: String,
    var displayValue: String,
    var id: String,
    var name: String,
    var shortDisplayName: String,
    var summary: String,
    var type: String,
    var value: Int
)