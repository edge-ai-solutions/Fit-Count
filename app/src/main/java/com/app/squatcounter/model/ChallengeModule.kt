package com.app.squatcounter.model

import com.app.squatcounter.model.repository.LeaderBoardFirebaseRepoImpl
import com.app.squatcounter.model.repository.LeaderBoardRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class ChallengeModule {

    @Binds
    abstract fun getLeaderBoardSource(repo: LeaderBoardFirebaseRepoImpl): LeaderBoardRepo
}