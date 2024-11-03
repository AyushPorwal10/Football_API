package com.example.football_api.MyActivities

import android.content.Intent
import android.health.connect.datatypes.units.Length
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.football_api.MyAdapters.TeamStatisticsAdapter
import com.example.football_api.MyApplication
import com.example.football_api.MyViewModels.TeamStatisticsViewModel
import com.example.football_api.My_ViewModelFactory.TeamFactory
import com.example.football_api.R
import com.example.football_api.TeamStatisticsItem
import com.example.football_api.databinding.ActivityTeamStatisticsBinding

class TeamStatisticsActivity : AppCompatActivity() {
    lateinit var binding : ActivityTeamStatisticsBinding

    lateinit var recyclerView : RecyclerView
    lateinit var teamStatisticsAdapter : TeamStatisticsAdapter
    lateinit var toolBar : Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityTeamStatisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countryId = intent.getIntExtra("countryId",0)
        val countryName = intent.getStringExtra("countryTeam")



        setSupportActionBar(binding.statisticsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = countryName

        recyclerView = binding.teamStatisticsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)


        teamStatisticsAdapter = TeamStatisticsAdapter()
        recyclerView.adapter = teamStatisticsAdapter






        if(countryId == 0){
            Toast.makeText(this , "No Country",Toast.LENGTH_LONG).show()
            return
        }
        val repository = (application as MyApplication).repository

        val teamViewModel = ViewModelProvider(this , TeamFactory(repository , "5700782","Europe/London","2023-08-06")).get(TeamStatisticsViewModel::class.java)

        teamViewModel.statisticsLiveData.observe(this , Observer {
            binding.progressBar.visibility = View.GONE
            val teamData : ArrayList<TeamStatisticsItem> = it
            teamStatisticsAdapter.submitList(teamData)
        })

    }
}