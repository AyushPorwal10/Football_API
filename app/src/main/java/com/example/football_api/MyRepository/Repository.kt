package com.example.football_api.MyRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.football_api.All_Teams
import com.example.football_api.Retrofit_Info.Retrofit_Interface
import com.example.football_api.TeamStatistics

class Repository(private val retrofitInterface: Retrofit_Interface) {

    private val countryList  =  MutableLiveData<All_Teams>()
    private val teamStatisticsMutable = MutableLiveData<TeamStatistics>()

    val countryListLiveData : LiveData<All_Teams>
        get() = countryList

    val teamStatistics : LiveData<TeamStatistics>
        get() = teamStatisticsMutable

    suspend fun getAllTeams() : All_Teams{
        val list = retrofitInterface.getAllTeams()
        countryList.postValue(list)
        return list
    }
    suspend fun getTeamStatistics(teamId : String , timeZone : String , fromDate : String) : TeamStatistics{
         val data = retrofitInterface.getTeamStatistics(teamId,timeZone,fromDate)
         teamStatisticsMutable.postValue(data)
         return data
    }
}