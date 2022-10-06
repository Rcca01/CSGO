package com.example.csgo.repository

import com.example.csgo.service.PandaScoreService

class PandaScoreRepository(
    private val pandaScoreService: PandaScoreService
) {
    fun getAllMatches() = pandaScoreService.getListMatches()
}