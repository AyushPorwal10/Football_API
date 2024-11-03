package com.example.football_api.MyViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.football_api.MyRepository.Repository
import com.example.football_api.TeamStatistics
import kotlinx.coroutines.launch

class TeamStatisticsViewModel(private val repository: Repository , private val teamId :String, private val timeZone :String
, private val fromDate:String) : ViewModel() {

    init{
        viewModelScope.launch{
            repository.getTeamStatistics(teamId , timeZone , fromDate)
        }
    }
    val statisticsLiveData : LiveData<TeamStatistics>
            get() = repository.teamStatistics

}