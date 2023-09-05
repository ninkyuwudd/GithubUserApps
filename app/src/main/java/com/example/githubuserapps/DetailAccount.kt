package com.example.githubuserapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailAccount : AppCompatActivity() {

    companion object {
        const val EXTRA_TITLE = "put_extra_title"
    }

    private lateinit var txt: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_account)

        val getName = intent.getStringExtra(EXTRA_TITLE)

        txt = findViewById(R.id.titleDetail)
        txt.text = getName
    }
}