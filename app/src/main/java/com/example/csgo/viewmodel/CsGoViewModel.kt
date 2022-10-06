package com.example.csgo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.csgo.model.Matches
import com.example.csgo.repository.PandaScoreRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CsGoViewModel constructor(private val pandaScoreRepository: PandaScoreRepository): ViewModel() {

    private val _listMatches = MutableLiveData<List<Matches>>(emptyList())
    val listMatches: LiveData<List<Matches>>
        get() = _listMatches

    private val _errorEndPoint = MutableLiveData<String>("")
    val errorEndPoint: LiveData<String>
        get() = _errorEndPoint

    fun getAllListMatches() {
        val response = pandaScoreRepository.getAllMatches()
        response.enqueue(object : Callback<List<Matches>>{
            override fun onResponse(call: Call<List<Matches>>, response: Response<List<Matches>>) {
                _listMatches.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Matches>>, t: Throwable) {
                _errorEndPoint.postValue(t.message)
            }
        })
    }
}
