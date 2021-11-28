package com.example.footballapp.models.imagemodel

import com.example.playfootballmvvm.model.imagemodel.Logos

data class Data(
    val abbr: String,
    val id: String,
    val logos: Logos,
    val name: String,
    val slug: String
)