package com.example.csgo.view.adapter

import com.example.csgo.model.Opponent
import com.example.csgo.model.Result

interface ClickListener<T> {
    fun onClick(data: List<Result>, opponent: List<Opponent>, leagueName: String, date: String)
}