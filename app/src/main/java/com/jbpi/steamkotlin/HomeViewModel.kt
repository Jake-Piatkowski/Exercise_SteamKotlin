package com.jbpi.steamkotlin

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.jbpi.steamkotlin.communication.*
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

class HomeViewModel(private var achievementRepo: AchievementRepo) : ViewModel() {

    val publishSubjectUiEvents: PublishSubject<UiEvent> = PublishSubject.create()
    val liveDataUiModel: MutableLiveData<UiModel> = MutableLiveData()

    private val transformerUiEventToAction: ObservableTransformer<UiEvent, Action> =
            ObservableTransformer { uiEvents ->
                uiEvents.publish { sharedUiEvents ->
                    Observable.merge(
                            sharedUiEvents.ofType(ButtonClickKillingFloor2::class.java).map { ShowKillingFloor2Achievements() },
                            sharedUiEvents.ofType(ButtonClickAlienSwarm::class.java).map { ShowAlienSwarmAchievements() }
                    )
                }
            }

    private val transformerActionToResult: ObservableTransformer<Action, Result> =
        ObservableTransformer { actions ->
            actions.publish { sharedActions ->
                Observable.merge(
                        sharedActions.ofType(ShowKillingFloor2Achievements::class.java).flatMap { achievementRepo.getAchievementsKillingFloor2() },
                        sharedActions.ofType(ShowAlienSwarmAchievements::class.java).flatMap { achievementRepo.getAchievementsAlienSwarm() }
                )
            }
        }

    private val transformerResultToUiModel: ObservableTransformer<Result, UiModel> =
        ObservableTransformer { results ->
            results.publish { sharedResults ->
                Observable.merge(
                        sharedResults.ofType(CurrentKillingFloor2Achievements::class.java).map { KillingFloor2UiModel(it.listAchievements) },
                        sharedResults.ofType(CurrentAlienSwarmAchievements::class.java).map { AlienSwarmUiModel(it.listAchievements) }
                )
            }
        }

    init {
        publishSubjectUiEvents
                .compose(transformerUiEventToAction)
                .compose(transformerActionToResult)
                .compose(transformerResultToUiModel)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { liveDataUiModel.setValue(it) }
    }
}