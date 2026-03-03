package com.example.intenttypes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity is the entry point of the application.
 * It demonstrates how to use Explicit and Implicit Intents.
 *
 * Theory:
 * An Intent is a messaging object you can use to request an action from another app component.
 */
class MainActivity : AppCompatActivity() {

    // The onCreate method is called when the activity is first created.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main) binds the XML layout to this activity.
        setContentView(R.layout.activity_main)

        // Finding the buttons from the XML layout using their IDs.
        val btnExplicit = findViewById<Button>(R.id.explicit_intent_button)
        val btnImplicit = findViewById<Button>(R.id.implicit_intent_button)

        /**
         * 1. EXPLICIT INTENT
         * Used to launch a specific component (Activity, Service, etc.) within your own app.
         * You specify the exact class name of the component you want to start.
         */
        btnExplicit.setOnClickListener {
            // Intent(Context, Class) creates an explicit intent for SecondActivity.
            val explicitIntent = Intent(this, SecondActivity::class.java)
            
            // startActivity(intent) sends the intent to the Android System to launch the activity.
            startActivity(explicitIntent)
        }

        /**
         * 2. IMPLICIT INTENT
         * Used when you want to perform an action but don't care which app handles it.
         * You specify an 'Action' (like ACTION_VIEW) and 'Data' (like a URL).
         */
        btnImplicit.setOnClickListener {
            val websiteUrl = "https://www.google.com"
            
            // Intent(Action, Uri) creates an implicit intent.
            // ACTION_VIEW is a standard action to display data to the user.
            // Uri.parse() converts the string URL into a Uri object.
            val implicitIntent = Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl))
            
            // The system looks for apps that can handle the ACTION_VIEW for a web URL (usually browsers).
            startActivity(implicitIntent)
        }
    }
}
