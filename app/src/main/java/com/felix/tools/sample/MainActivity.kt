package com.felix.tools.sample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.felix.tools.sample.view.UIStatusActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_ui_status.setOnClickListener {
            startActivity(Intent(this, UIStatusActivity::class.java))
        }
    }
}
