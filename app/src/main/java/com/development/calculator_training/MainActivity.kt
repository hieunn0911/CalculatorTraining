package com.development.calculator_training

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.development.calculator_training.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.containerView, CalculatorFragment())
            .commit()
    }
}
