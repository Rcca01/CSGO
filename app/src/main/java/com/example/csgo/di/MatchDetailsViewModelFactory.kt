package com.example.csgo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.csgo.repository.PandaScoreRepository
import com.example.csgo.viewmodel.MatchDetailsViewModel

class MatchDetailsViewModelFactory(private val repository: PandaScoreRepository):
    ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MatchDetailsViewModel::class.java)) {
                return MatchDetailsViewModel(this.repository) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
}