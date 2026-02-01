package com.example.gridview

import android.os.Bundle
import android.view.View
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * MainActivity for the Hotel Management System GridView application.
 * This activity initializes the GridView with hotel management categories.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Enable edge-to-edge display for modern look
        enableEdgeToEdge()
        
        // Set the layout for the activity
        setContentView(R.layout.activity_main)

        // Handle window insets to ensure the UI isn't obscured by system bars
        val mainView: View? = findViewById(R.id.main)
        mainView?.let { view ->
            ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        // 1. Find the GridView from the layout
        val gridView: GridView = findViewById(R.id.hotelGridView)

        // 2. Prepare the data source for the GridView
        val hotelModules = listOf(
            HotelItem("Bookings", android.R.drawable.ic_menu_today),
            HotelItem("Rooms", android.R.drawable.ic_menu_myplaces),
            HotelItem("Dining", android.R.drawable.ic_menu_view),
            HotelItem("Staff", android.R.drawable.ic_menu_info_details),
            HotelItem("Services", android.R.drawable.ic_menu_manage),
            HotelItem("Payments", android.R.drawable.ic_menu_save),
            HotelItem("Inventory", android.R.drawable.ic_menu_agenda),
            HotelItem("Feedback", android.R.drawable.ic_menu_edit)
        )

        // 3. Create an instance of our custom HotelAdapter
        val adapter = HotelAdapter(this, hotelModules)

        // 4. Attach the adapter to the GridView
        gridView.adapter = adapter

        // 5. Handle item clicks on the GridView
        gridView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = hotelModules[position]
            // Show a toast message when an item is clicked
            Toast.makeText(this, "Opening ${selectedItem.name}...", Toast.LENGTH_SHORT).show()
        }
    }
}
