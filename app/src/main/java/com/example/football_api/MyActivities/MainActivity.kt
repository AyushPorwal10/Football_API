package com.example.football_api.MyActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.football_api.Data
import com.example.football_api.MyAdapters.AllTeamsAdapter
import com.example.football_api.MyApplication
import com.example.football_api.MyRepository.Repository
import com.example.football_api.MyViewModels.MainViewModel
import com.example.football_api.My_ViewModelFactory.MainFactory
import com.example.football_api.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var  binding : ActivityMainBinding
    lateinit var repository: Repository
    lateinit var mainViewModel: MainViewModel
    lateinit var countryListAdapter: AllTeamsAdapter
    lateinit var countryRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.title = "Football Stats"

        countryRecyclerView = binding.countryRecylerView
        countryRecyclerView.layoutManager = LinearLayoutManager(this)


        repository = (application as MyApplication).repository

        mainViewModel = ViewModelProvider(this , MainFactory(repository)).get(MainViewModel::class.java)

        countryListAdapter = AllTeamsAdapter(this)

        countryRecyclerView.adapter = countryListAdapter

        mainViewModel.countryListLiveData.observe(this , Observer {

            val countryNameLogo: List<Data> = it.data
            binding.progressBar.visibility = View.GONE
            Log.d("Testing","Total countries are ${countryNameLogo.size}")
            countryListAdapter.setCountryList(countryNameLogo)
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                countryListAdapter.filter(newText ?: "")
                return true
            }
        })
    }
}