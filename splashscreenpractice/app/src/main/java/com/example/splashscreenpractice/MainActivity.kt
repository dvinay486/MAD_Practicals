package com.example.splashscreenpractice

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * MainActivity is the screen that appears after the splash screen.
 * It typically contains the main functionality of your app.
 */
class MainActivity : AppCompatActivity() {

    /**
     * onCreate is called when the activity is starting.
     * This is where most initialization should go.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        /**
         * enableEdgeToEdge() allows the app to draw content from edge to edge of the screen,
         * including behind the status and navigation bars.
         */
        enableEdgeToEdge()
        
        /**
         * setContentView() connects this Activity to its UI layout file.
         * R.layout.activity_main refers to res/layout/activity_main.xml.
         */
        setContentView(R.layout.activity_main)
        
        /**
         * This listener adjusts the padding of the root view ('main') so that 
         * interactive elements aren't obscured by system UI (like the navigation bar).
         */
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
