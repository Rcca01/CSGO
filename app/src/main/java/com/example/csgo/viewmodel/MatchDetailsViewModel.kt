package com.example.csgo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.csgo.model.Players
import com.example.csgo.repository.PandaScoreRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchDetailsViewModel(private val pandaScoreRepository: PandaScoreRepository): ViewModel() {
    private val _listPlayerFirst = MutableLiveData<List<Players>>(emptyList())
    val listPlayerFirst: LiveData<List<Players>>
        get() = _listPlayerFirst

    private val _listPlayerSecond = MutableLiveData<List<Players>>(emptyList())
    val listPlayerSecond: LiveData<List<Players>>
        get() = _listPlayerSecond

    private val _errorEndPointFirst = MutableLiveData<String>("")
    val errorEndPointFirst: LiveData<String>
        get() = _errorEndPointFirst

    private val _errorEndPointSecond = MutableLiveData<String>("")
    val errorEndPointSecond: LiveData<String>
        get() = _errorEndPointSecond

    fun getAllListPlayersFirst(teamId:String) {
        val response = pandaScoreRepository.getAllPlayers(teamId)
        response.enqueue(object : Callback<List<Players>> {
            override fun onResponse(call: Call<List<Players>>, response: Response<List<Players>>) {
                _listPlayerFirst.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Players>>, t: Throwable) {
                _errorEndPointFirst.postValue(t.message)
            }
        })
    }

    fun getAllListPlayersSecond(teamId:String) {
        val response = pandaScoreRepository.getAllPlayers(teamId)
        response.enqueue(object : Callback<List<Players>> {
            override fun onResponse(call: Call<List<Players>>, response: Response<List<Players>>) {
                _listPlayerSecond.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Players>>, t: Throwable) {
                _errorEndPointSecond.postValue(t.message)
            }
        })
    }
}