package com.example.ryanrileymtgfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ryanrileymtgfinal.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//    private val binding: ActivityMainBinding by lazy {
//        ActivityMainBinding.inflate(layoutInflater)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}