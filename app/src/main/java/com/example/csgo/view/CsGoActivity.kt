package com.example.csgo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.csgo.R
import com.example.csgo.databinding.ActivityCsGoBinding
import com.example.csgo.di.CsGoViewModelFactory
import com.example.csgo.repository.PandaScoreRepository
import com.example.csgo.service.PandaScoreService
import com.example.csgo.view.adapter.RecycleviewItemAdapter
import com.example.csgo.viewmodel.CsGoViewModel

class CsGoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCsGoBinding
    lateinit var viewModel: CsGoViewModel
    private val retrofitService = PandaScoreService.getInstance()
    val adapter = RecycleviewItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cs_go)

        binding = ActivityCsGoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, CsGoViewModelFactory(PandaScoreRepository(retrofitService)))[CsGoViewModel::class.java]
        binding.recycleView.adapter = adapter
        viewModel.listMatches.observe(this, Observer {
            adapter.setMatchesList(it)
        })
        viewModel.errorEndPoint.observe(this, Observer {
            Log.d("errorapi", it)
        })
        viewModel.getAllListMatches()
    }
}