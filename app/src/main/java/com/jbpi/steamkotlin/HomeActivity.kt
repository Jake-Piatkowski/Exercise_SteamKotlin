package com.jbpi.steamkotlin

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.widget.Button
import com.jakewharton.rxbinding2.view.RxView
import com.jbpi.steamkotlin.communication.*
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class HomeActivity : LifecycleActivity() {

    lateinit var component: HomeActivityComponent
    @Inject lateinit var viewModelFactory: ViewModelFactory

    private lateinit var buttonGameKillingFloor2: Button
    private lateinit var buttonGameAlienSwarm: Button

    private lateinit var viewModelHome: HomeViewModel
    private lateinit var disposableUiEvents: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)
        injectDependencies()

        this.viewModelHome = ViewModelProviders.of(this, viewModelFactory as ViewModelProvider.Factory).get(HomeViewModel::class.java)

        this.buttonGameKillingFloor2 = findViewById(R.id.home_button_game_killingfloor2) as Button
        this.buttonGameAlienSwarm = findViewById(R.id.home_button_game_alienswarm) as Button

        // UIEvent -- 1!
        this.disposableUiEvents =
                Observable.merge(
                        RxView.clicks(this.buttonGameKillingFloor2)
                                .map { _ -> ButtonClickKillingFloor2() },
                        RxView.clicks(this.buttonGameAlienSwarm)
                                .map { _ -> ButtonClickAlienSwarm() })
                        .subscribe { uiEvent -> viewModelHome.publishSubjectUiEvents.onNext(uiEvent) }

        this.viewModelHome.liveDataUiModel.observe(this, Observer {

            val intent: Intent = Intent(this, DetailActivity::class.java)

            when (it) {
                is KillingFloor2UiModel -> {

                    intent.putExtra("blah", it.listAchievements)
                }
                is AlienSwarmUiModel -> {

                    intent.putExtra("blah", it.listAchievements)
                }
            }

            startActivity(intent)
        })
    }

    private fun injectDependencies() {

        component = DaggerHomeActivityComponent.builder().build()
        component.inject(this)
    }
}
