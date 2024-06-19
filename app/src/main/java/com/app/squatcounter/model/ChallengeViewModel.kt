package com.app.squatcounter.model

import android.app.Application
import android.view.animation.Transformation
import androidx.lifecycle.*
import com.app.squatcounter.domain.LeaderBoardUseCase
import com.app.squatcounter.domain.PlayerModel
import com.app.squatcounter.util.ChallengeEnum
import com.app.squatcounter.util.SharedPref
import com.google.firebase.FirebaseApp
import dagger.assisted.Assisted
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

class ChallengeViewModel  : AndroidViewModel {
    constructor(application: Application) : super(application)
    private val _LeaderBoardList = MutableLiveData<List<PlayerModel>?>()
    val leaderBoardList: LiveData<List<PlayerModel>?>  = _LeaderBoardList

    private val _name = MutableLiveData<String?>()
    val name: LiveData<String?> get() = _name

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _type = MutableLiveData<String?>()
    val type: LiveData<String?>
        get() = _type

    fun setName(userName: String) {
        _name.value = userName
    }

    fun setScore(score: Int) {
        _score.value = score
    }

    fun setType(type: String) {
        _type.value = type
    }

    init {
      //  FirebaseApp.initializeApp()
        _type.value = ChallengeEnum.SQUAT.challengeName
        //_score.value = 0
    }

   // val getLeaderBoardList:LiveData<List<PlayerModel>?>  = getFilteredData()
   fun getLeaderBoardList() {
       _LeaderBoardList.value = getFilteredData()
   }

    private fun getFilteredData(): MutableList<PlayerModel>? =
        SharedPref.getInstance(getApplication())?.getSquatCountData() as MutableList<PlayerModel>?

    fun addScoreToLeaderBoardList() {
       // if (score.value!! > 0) {
           var leaderboardList: MutableList<PlayerModel>?=  getFilteredData()
if(leaderboardList == null){
    leaderboardList = ArrayList<PlayerModel>()
}
            leaderboardList?.add(PlayerModel(SharedPref.getInstance(getApplication())?.getString("name") ?: "noname", SharedPref.getInstance(getApplication())?.getString("score").toString(), "Squat"))
            //val exType = type.value!!
            SharedPref.getInstance(getApplication())?.saveSquatCountData(leaderboardList)
          /*  leaderBoardUseCase.addScoreToLeaderBoardList(
                PlayerModel(name.value ?: "noname", score.value.toString(), "Squat"))*/
       // }
    }

}