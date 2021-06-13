package com.example.rs.raf.projekatjun.stefan_budimac_rn618.presentaion.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.R
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initListeners()
    }

    private fun initListeners() {
        binding.btnAddEvent.setOnClickListener {
            val intent = Intent(this, NewEventActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}