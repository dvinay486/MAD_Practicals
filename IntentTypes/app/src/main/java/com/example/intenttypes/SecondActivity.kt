package com.example.intenttypes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * This is the second activity, launched by an explicit intent from MainActivity.
 */
class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This sets the user interface for the Activity from a layout resource.
        setContentView(R.layout.activity_second)
    }
}
