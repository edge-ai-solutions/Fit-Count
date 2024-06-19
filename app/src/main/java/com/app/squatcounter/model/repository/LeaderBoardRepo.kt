package com.app.squatcounter.model.repository

import androidx.lifecycle.LiveData
import com.app.squatcounter.domain.PlayerModel

interface LeaderBoardRepo {

    fun addScoreToLeaderBoardList(player: PlayerModel)

    fun getLeaderBoardList(): LiveData<List<PlayerModel>>

}