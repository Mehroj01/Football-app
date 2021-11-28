package com.example.footballapp.respository

import com.example.footballapp.room.League

object Extra {
    private var league: League? = null

    fun setLeague1(league: League) {
        this.league = league
    }

    fun getLeague1(): League? {
        return league
    }

}