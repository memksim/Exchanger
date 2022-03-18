package com.memksim.exchanger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.memksim.exchanger.ui.views.ListPageFragment

/**
 * https://www.cbr-xml-daily.ru/daily_json.js
 * */

const val TAG = "test"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.mainContainer, ListPageFragment())
                .commit()
        }
    }
}