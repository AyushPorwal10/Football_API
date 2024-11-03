package com.example.football_api

data class TeamStatisticsItem(
    val away: Away,
    val home: Home,
    val leagueId: Int,
    val leagueName: String,
    val season: Int,
    val total: Total
)