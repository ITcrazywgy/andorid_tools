package com.felix.tools.sample.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.felix.tools.sample.R
import com.felix.tools.view.loading.UIStatus
import kotlinx.android.synthetic.main.activity_ui_status.*

class UIStatusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_status)

        val statusHelper = UIStatus.Builder()
            .content(content)
            .statusViewAdapter(GlobalStatusAdapter())
            .build()

        statusHelper.showLoading()
        val handler = Handler()
        handler.postDelayed({
            statusHelper.showError()
        }, 5000)
        handler.postDelayed({
            statusHelper.showContent()
        }, 10000)

        handler.postDelayed({
            statusHelper.showEmpty()
        }, 15000)

    }
}
