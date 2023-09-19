package com.example.githubuserapps.ui.page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapps.R
import com.example.githubuserapps.databinding.ActivityLovedListBinding
import com.example.githubuserapps.factory.LovedViewModelFactory

class LovedListActivity : AppCompatActivity() {


    private var _lovedActivityBinding : ActivityLovedListBinding? = null
    private val binding get() = _lovedActivityBinding
    private lateinit var adapter: LovedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _lovedActivityBinding = ActivityLovedListBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val lovedViewModel = obtainViewModel(this@LovedListActivity)
        lovedViewModel.getAllNotes().observe(this) { lovedList ->

                adapter.setListLoved(lovedList)

        }

        adapter = LovedAdapter()
        binding?.rvLoved?.layoutManager = LinearLayoutManager(this)
        binding?.rvLoved?.setHasFixedSize(true)
        binding?.rvLoved?.adapter = adapter


//        binding?.fabAdd?.setOnClickListener {
//            val intent = Intent(this@MainActivity, NoteAddUpdateActivity::class.java)
//            startActivity(intent)
//        }
    }


    private fun obtainViewModel(activity: AppCompatActivity): LovedViewModel {
        val factory = LovedViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(LovedViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        _lovedActivityBinding= null
    }
}