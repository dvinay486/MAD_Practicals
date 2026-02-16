package com.example.splashscreenpractice

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * SplashActivity2 serves as the entry point of the application.
 * It demonstrates the use of the Android SplashScreen API and handles the transition 
 * to the main screen after a delay.
 */
class SplashActivity2 : AppCompatActivity() {

    /**
     * Called when the activity is first created.
     * This is where you should do all of your normal static set up: create views, 
     * bind data to lists, etc.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        /* 
           installSplashScreen() must be called before super.onCreate().
           It handles the SplashScreen animation provided by the Android 12+ 
           SplashScreen API and ensures backward compatibility.
        */
        installSplashScreen()
        
        super.onCreate(savedInstanceState)
        
        /* 
           enableEdgeToEdge() enables drawing behind system bars (status bar and navigation bar),
           providing a more immersive, full-screen experience.
        */
        enableEdgeToEdge()
        
        /* 
           setContentView() sets the activity content from a layout resource.
           In this case, it loads 'res/layout/splash_activity.xml'.
        */
        setContentView(R.layout.splash_activity)
        
        /* 
           setOnApplyWindowInsetsListener is used to handle system insets (like the height 
           of the status bar or navigation bar) manually. This ensures that the UI components 
           don't get covered by system UI elements when edge-to-edge is enabled.
        */
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        /* 
           Handler is used to schedule a task to be executed after a specified delay.
           Looper.getMainLooper() ensures the task runs on the main (UI) thread.
           postDelayed() takes a Runnable and a delay in milliseconds.
        */
        Handler(Looper.getMainLooper()).postDelayed({
            /* 
               Intent is used to start another activity. 
               Here we are navigating from SplashActivity2 to MainActivity.
            */
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            
            /* 
               finish() closes the current activity so the user cannot return to the 
               splash screen by pressing the back button.
            */
            finish()
        }, 3000) // 3000 milliseconds = 3 seconds delay
    }
}
