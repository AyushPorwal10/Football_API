package com.example.football_api

import android.app.Application
import com.example.football_api.MyRepository.Repository
import com.example.football_api.Retrofit_Info.Retrofit_Interface
import com.example.football_api.Retrofit_Info.Retrofit_OBJ

class MyApplication : Application() {

    val db = Retrofit_OBJ.getInstance().create(Retrofit_Interface::class.java)

    val repository = Repository(db)
}