package com.jbpi.steamkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        Log.e("TEST", "ilosc: " + intent.getParcelableArrayExtra("blah").size)
    }
}
