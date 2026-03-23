package com.example.navigationdrawer

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

/**
 * MainActivity is the entrance of our application.
 * It manages the Navigation Drawer and switches between different Fragments.
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the Toolbar from layout and set it as the Action Bar for the activity
        // Toolbar is a flexible view that can be used for branding, navigation, and actions.
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Initialize DrawerLayout and NavigationView
        // DrawerLayout is the container that allows the navigation drawer to slide in from the edge.
        drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        
        // Set the listener for menu item clicks
        navigationView.setNavigationItemSelectedListener(this)

        // ActionBarDrawerToggle connects the DrawerLayout to the Toolbar
        // It provides the "hamburger" icon and handles the open/close animations
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, // String resource for accessibility
            R.string.navigation_drawer_close // String resource for accessibility
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState() // Synchronize the indicator with the drawer state

        // Load the default fragment (HomeFragment) when the app starts for the first time
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
            navigationView.setCheckedItem(R.id.nav_home)
        }

        // Handle the Back button press using OnBackPressedDispatcher.
        // This is the modern way to handle back navigation in Android.
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    // If the drawer is open, close it
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    // If drawer is closed, allow the default back button behavior
                    isEnabled = false // Disable this callback
                    onBackPressedDispatcher.onBackPressed() // Trigger default back action
                    isEnabled = true // Re-enable for future use
                }
            }
        })
    }

    /**
     * This method is called when an item in the navigation menu is clicked.
     * @param item The selected menu item
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks based on their ID defined in drawer_menu.xml
        when (item.itemId) {
            R.id.nav_home -> replaceFragment(HomeFragment())
            R.id.nav_gallery -> replaceFragment(GalleryFragment())
            R.id.nav_slideshow -> replaceFragment(SlideshowFragment())
        }
        // Close the drawer after an item is selected
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    /**
     * Helper method to replace the current fragment in the container.
     * @param fragment The new fragment to display
     */
    private fun replaceFragment(fragment: Fragment) {
        // FragmentManager is used to handle transactions (add, remove, replace) between fragments
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}