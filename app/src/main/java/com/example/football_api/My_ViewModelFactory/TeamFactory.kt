package com.example.football_api.My_ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.football_api.MyRepository.Repository
import com.example.football_api.MyViewModels.TeamStatisticsViewModel

class TeamFactory(private val repository: Repository ,private val teamId :String , private val timeZone : String , private val fromDate : String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TeamStatisticsViewModel(repository , teamId , timeZone , fromDate) as T
    }
}