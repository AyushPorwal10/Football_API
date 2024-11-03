package com.example.football_api.MyAdapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.football_api.Data
import com.example.football_api.MyActivities.TeamStatisticsActivity
import com.example.football_api.databinding.CountryListBinding

class AllTeamsAdapter(private val context: Context) : ListAdapter<Data , AllTeamsAdapter.TeamViewHolder>(CountryDiffCallback()) {

    private var originalList : List<Data> = listOf()
    private var filteredList : List<Data> = listOf()

    fun setCountryList(list : List<Data>){
        originalList  = list
        filteredList = list
        submitList(filteredList)
    }

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            originalList
        } else {
            originalList.filter { it.name.contains(query, ignoreCase = true) }
        }
        submitList(filteredList)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = CountryListBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(filteredList[position])

        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(context , TeamStatisticsActivity::class.java)
            Log.d("TeamData","Sending Team Id ${getItem(position).id}")

            intent.putExtra("countryId",getItem(position).id)
            intent.putExtra("countryTeam",getItem(position).name)
            context.startActivity(intent)
        })

    }
    override fun getItemCount(): Int = filteredList.size

    class TeamViewHolder(private val binding: CountryListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(team : Data){
            binding.teamName.text = team.name


            Glide.with(binding.root.context)
                .load(team.logo)
                .into(binding.teamLogo)
        }
    }





    class CountryDiffCallback : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id // Assuming 'code' is unique for each country
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem // Use the data class comparison
        }
    }

}