package com.example.football_api.MyAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.football_api.Data
import com.example.football_api.TeamStatistics
import com.example.football_api.TeamStatisticsItem
import com.example.football_api.databinding.TeamStatisticsBinding

class TeamStatisticsAdapter :
    ListAdapter<TeamStatisticsItem, TeamStatisticsAdapter.TeamStatisticsViewHolder>(
        TeamStatisticsDiff()
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamStatisticsViewHolder {
        val viewHolder =
            TeamStatisticsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamStatisticsViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: TeamStatisticsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TeamStatisticsViewHolder(private val binding: TeamStatisticsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teamStatisticsItem: TeamStatisticsItem) {
            binding.leagueSeason = teamStatisticsItem
            binding.homeGames = teamStatisticsItem.home.games
            binding.awayGames = teamStatisticsItem.away.games
        }
    }

    class TeamStatisticsDiff : DiffUtil.ItemCallback<TeamStatisticsItem>() {
        override fun areItemsTheSame(
            oldItem: TeamStatisticsItem,
            newItem: TeamStatisticsItem
        ): Boolean {
            return oldItem.leagueId == newItem.leagueId
        }

        override fun areContentsTheSame(
            oldItem: TeamStatisticsItem,
            newItem: TeamStatisticsItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}