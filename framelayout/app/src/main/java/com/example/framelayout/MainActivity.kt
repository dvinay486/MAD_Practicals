package com.example.framelayout

import android.os.Bundle
import android.widget.Toast
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/**
 * MSBTE Course: 316006 - Mobile Application Development
 * 
 * This Activity serves as the controller for our E-commerce Product Page.
 * Students should observe how the XML layouts (Frame, Relative, Table) 
 * are inflated and how we can interact with views inside them.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        /* 
           setContentView links the Kotlin code to our XML design.
           'R.layout.activity_main' refers to activity_main.xml in the layout folder.
        */
        setContentView(R.layout.activity_main)

        /*
           HOW TO USE VIEW INTERACTION:
           Even though we used different layouts, we access child views the same way.
           Here we find the Button that was placed inside the RelativeLayout.
        */
        val buyButton: Button = findViewById(R.id.btn_buy)

        // Setting a click listener to make the app interactive
        buyButton.setOnClickListener {
            Toast.makeText(this, "Product added to cart!", Toast.LENGTH_SHORT).show()
        }
    }
}
