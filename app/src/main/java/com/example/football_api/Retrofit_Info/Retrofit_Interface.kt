package com.example.football_api.Retrofit_Info

import com.example.football_api.All_Teams
import com.example.football_api.TeamStatistics
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Retrofit_Interface {

    @GET("teams")
    suspend fun getAllTeams() :All_Teams

    @GET("teams/statistics/{teamId}")

    suspend fun getTeamStatistics(
        @Path("teamId") teamId: String,
        @Query("timezone") timezone: String,
        @Query("fromDate") fromDate: String
    ): TeamStatistics
}