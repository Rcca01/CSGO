package com.example.csgo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.csgo.databinding.ActivityCsGoBinding
import com.example.csgo.di.CsGoViewModelFactory
import com.example.csgo.model.Result
import com.example.csgo.repository.PandaScoreRepository
import com.example.csgo.service.PandaScoreService
import com.example.csgo.view.adapter.ClickListener
import com.example.csgo.view.adapter.RecycleviewItemAdapter
import com.example.csgo.viewmodel.CsGoViewModel
import android.content.Intent
import android.view.View
import com.example.csgo.R
import com.example.csgo.model.Opponent
import com.example.csgo.utils.Utils
import com.example.csgo.view.adapter.FIRST_OPPONENT
import com.example.csgo.view.adapter.SECOND_OPPONENT

class CsGoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCsGoBinding
    lateinit var viewModel: CsGoViewModel
    private val retrofitService = PandaScoreService.getInstance()
    private val adapter = RecycleviewItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cs_go)

        binding = ActivityCsGoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, CsGoViewModelFactory(PandaScoreRepository(retrofitService)))[CsGoViewModel::class.java]
        binding.recycleView.adapter = adapter


        viewModel.listMatches.observe(this, Observer {
            binding.progressBarMatches.visibility = View.GONE
            adapter.setMatchesList(it)
        })
        viewModel.errorEndPoint.observe(this, Observer {
            binding.progressBarMatches.visibility = View.GONE
            Log.d("errorapi", it)
        })
        viewModel.getAllListMatches()


        adapter.setOnItemClickListener(object : ClickListener<List<Result>>{
            override fun onClick(data: List<Result>, opponent: List<Opponent>, leagueName: String, date: String) {
                val mIntent = Intent(baseContext, MatchDetailsActivity::class.java)
                val bundle = Bundle()
                bundle.putLong("teamIdFirst", data[FIRST_OPPONENT].teamID)
                bundle.putLong("teamIdSecond", data[SECOND_OPPONENT].teamID)

                bundle.putString("nameTeamFirst", opponent[FIRST_OPPONENT].opponent.name)
                bundle.putString("imageTeamFirst", opponent[FIRST_OPPONENT].opponent.imageURL)

                bundle.putString("nameTeamSecond", opponent[SECOND_OPPONENT].opponent.name)
                bundle.putString("imageTeamSecond", opponent[SECOND_OPPONENT].opponent.imageURL)

                bundle.putString("leagueName", leagueName)
                bundle.putString("dateMatch", Utils().getDayFromDateString(date))

                mIntent.putExtras(bundle)
                startActivity(mIntent)
            }
        })
    }
}