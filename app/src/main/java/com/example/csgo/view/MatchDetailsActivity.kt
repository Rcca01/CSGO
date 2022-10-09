package com.example.csgo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.csgo.R
import com.example.csgo.databinding.ActivityCsGoBinding
import com.example.csgo.databinding.ActivityMatchDetailsBinding
import com.example.csgo.di.CsGoViewModelFactory
import com.example.csgo.di.MatchDetailsViewModelFactory
import com.example.csgo.model.Opponent
import com.example.csgo.repository.PandaScoreRepository
import com.example.csgo.service.PandaScoreService
import com.example.csgo.view.adapter.AdapterPlayersFirst
import com.example.csgo.view.adapter.AdapterPlayersSecond
import com.example.csgo.view.adapter.RecycleviewItemAdapter
import com.example.csgo.viewmodel.CsGoViewModel
import com.example.csgo.viewmodel.MatchDetailsViewModel

class MatchDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMatchDetailsBinding
    lateinit var viewModel: MatchDetailsViewModel
    private val retrofitService = PandaScoreService.getInstance()
    private val adapterFirst = AdapterPlayersFirst()
    private val adapterSecond = AdapterPlayersSecond()
    private val loadingGetDataMatch = mutableListOf<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_details)

        binding = ActivityMatchDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, MatchDetailsViewModelFactory(
            PandaScoreRepository(retrofitService)))[MatchDetailsViewModel::class.java]

        binding.progressBarDetailsMatch.visibility = View.VISIBLE
        binding.recycleView1.adapter = adapterFirst
        binding.recycleView2.adapter = adapterSecond

        viewModel.listPlayerFirst.observe(this, Observer {
            adapterFirst.setPlayersList(it)
            if(loadingGetDataMatch.isNotEmpty())
                binding.progressBarDetailsMatch.visibility = View.GONE
            loadingGetDataMatch.add(true)
        })

        viewModel.listPlayerSecond.observe(this, Observer {
            adapterSecond.setPlayersList(it)
            if(loadingGetDataMatch.isEmpty())
                binding.progressBarDetailsMatch.visibility = View.GONE
            loadingGetDataMatch.add(true)
        })

        val bundle = intent.extras
        bundle?.let {
            viewModel.getAllListPlayersFirst(bundle.getLong("teamIdFirst").toString())
            viewModel.getAllListPlayersSecond(bundle.getLong("teamIdSecond").toString())
            Glide.with(this).load(bundle.getString("imageTeamFirst")).into(binding.clubImage1)
            Glide.with(this).load(bundle.getString("imageTeamSecond")).into(binding.clubImage2)
            binding.clubName1.text = bundle.getString("nameTeamFirst")
            binding.clubName2.text = bundle.getString("nameTeamSecond")
            setUpActionBar(bundle.getString("leagueName") ?:  "League + serie")
            binding.tvDateMatch.text = bundle.getString("dateMatch")
        }

    }

    private fun setUpActionBar(title: String) {
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = title
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}