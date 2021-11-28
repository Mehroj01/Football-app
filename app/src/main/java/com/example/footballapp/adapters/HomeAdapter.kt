package com.example.footballapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.footballapp.databinding.RvItemBinding

import com.example.footballapp.respository.TeamRepository
import com.example.footballapp.retrofit.ApiClient
import com.example.footballapp.room.League
import com.example.footballapp.utils.NetworkHelper
import com.example.footballapp.utils.TeamResource
import com.example.footballapp.viewmodels.MainViewModel
import com.example.footballapp.viewmodels.ViewModelFactory


import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class HomeAdapter(
    val context: Context,
    private var leagues: List<League>,
    val listener: HomeAdapter.OnItemClickListener
) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private lateinit var mainViewModel: MainViewModel

    class ViewHolder(var binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            // Define click listener for the ViewHolder's View.
            binding.apply {

            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val binding =
            RvItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            teamName.text = leagues[position].name
            Picasso.get().load(leagues[position].image).into(teamImage)
            bindChildView(leagues[position].id, holder.binding, position)

            enter.setOnClickListener {
                listener.onClick(leagues[position])
            }
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = leagues.size


    interface OnItemClickListener {
        fun onClick(league: League)
    }

    private fun bindChildView(id: String, binding: RvItemBinding, position: Int) {
        mainViewModel = ViewModelProvider(
            context as FragmentActivity,
            ViewModelFactory(
                TeamRepository(ApiClient.apiService),
                networkHelper = NetworkHelper(context)
            )
        )[MainViewModel::class.java]
        context.lifecycleScope.launch {
            mainViewModel.fetchTeamsByLeague(id).collect {
                when (it) {
                    is TeamResource.Error -> {

                    }
                    is TeamResource.Loading -> {
                        binding.apply {
                            lottie.visibility = View.VISIBLE
                            constraint.visibility = View.GONE
                        }

                    }
                    is TeamResource.Success -> {

                        binding.apply {
                            lottie.visibility = View.GONE
                            constraint.visibility = View.VISIBLE
                            country.text = leagues[position].ab
                            team1Name.text = it.body.data.standings[0].team.abbreviation
                            team2Name.text = it.body.data.standings[1].team.abbreviation
                            team3Name.text = it.body.data.standings[2].team.abbreviation
                            team4Name.text = it.body.data.standings[3].team.abbreviation
                            team5Name.text = it.body.data.standings[4].team.abbreviation
                            team1ScoreDec.text = it.body.data.standings[0].team.name
                            team2ScoreDec.text = it.body.data.standings[1].team.name
                            team3ScoreDec.text = it.body.data.standings[2].team.name
                            team4ScoreDec.text = it.body.data.standings[3].team.name
                            team5ScoreDec.text = it.body.data.standings[4].team.name
                            team1Score.text = it.body.data.standings[0].stats[0].value.toString()
                            team2Score.text = it.body.data.standings[1].stats[0].value.toString()
                            team3Score.text = it.body.data.standings[2].stats[0].value.toString()
                            team4Score.text = it.body.data.standings[3].stats[0].value.toString()
                            team5Score.text = it.body.data.standings[4].stats[0].value.toString()
                            team21Score.text = it.body.data.standings[0].stats[0].value.toString()
                            team22Score.text = it.body.data.standings[1].stats[0].value.toString()
                            team23Score.text = it.body.data.standings[2].stats[0].value.toString()
                            team24Score.text = it.body.data.standings[3].stats[0].value.toString()
                            team25Score.text = it.body.data.standings[4].stats[0].value.toString()
                            team31Score.text = it.body.data.standings[0].stats[0].value.toString()
                            team32Score.text = it.body.data.standings[1].stats[0].value.toString()
                            team33Score.text = it.body.data.standings[2].stats[0].value.toString()
                            team34Score.text = it.body.data.standings[3].stats[0].value.toString()
                            team35Score.text = it.body.data.standings[4].stats[0].value.toString()
                            team41Score.text = it.body.data.standings[0].stats[0].value.toString()
                            team42Score.text = it.body.data.standings[1].stats[0].value.toString()
                            team43Score.text = it.body.data.standings[2].stats[0].value.toString()
                            team44Score.text = it.body.data.standings[3].stats[0].value.toString()
                            team45Score.text = it.body.data.standings[4].stats[0].value.toString()
                            team51Score.text = it.body.data.standings[0].stats[0].value.toString()
                            team52Score.text = it.body.data.standings[1].stats[0].value.toString()
                            team53Score.text = it.body.data.standings[2].stats[0].value.toString()
                            team54Score.text = it.body.data.standings[3].stats[0].value.toString()
                            team55Score.text = it.body.data.standings[4].stats[0].value.toString()
                        }

                        Picasso.get().load(it.body.data.standings[0].team.logos[0].href)
                            .into(binding.team1Image)
                        Picasso.get().load(it.body.data.standings[1].team.logos[0].href)
                            .into(binding.team2Image)
                        Picasso.get().load(it.body.data.standings[2].team.logos[0].href)
                            .into(binding.team3Image)
                        Picasso.get().load(it.body.data.standings[3].team.logos[0].href)
                            .into(binding.team4Image)
                        Picasso.get().load(it.body.data.standings[4].team.logos[0].href)
                            .into(binding.team5Image)


                    }
                }
            }
        }

    }
}
