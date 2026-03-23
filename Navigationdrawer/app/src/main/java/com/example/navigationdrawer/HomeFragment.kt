package com.example.navigationdrawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Fragment representing the Home screen.
 * A Fragment is a modular portion of an activity's user interface.
 */
class HomeFragment : Fragment() {

    // onCreateView is called to have the fragment instantiate its user interface view.
    override fun onCreateView(
        inflater: LayoutInflater, // Used to inflate any views in the fragment
        container: ViewGroup?,    // If non-null, this is the parent view that the fragment's UI should be attached to
        savedInstanceState: Bundle? // If non-null, this fragment is being re-constructed from a previous saved state
    ): View? {
        // Inflate the layout for this fragment (fragment_home.xml)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}