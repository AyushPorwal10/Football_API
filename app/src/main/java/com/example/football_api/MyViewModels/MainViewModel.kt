package com.example.football_api.MyViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.football_api.All_Teams
import com.example.football_api.MyRepository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    init{
        viewModelScope.launch{
           repository.getAllTeams()
        }
    }

    val countryListLiveData : LiveData<All_Teams>
        get() =  repository.countryListLiveData

}