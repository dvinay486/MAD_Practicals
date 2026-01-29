package com.example.checkboxradiobutton

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity class to demonstrate the use of CheckBox and RadioButton components.
 * This practical is part of MSBTE Course 316006.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout file for this activity
        setContentView(R.layout.activity_main)

        // Declaring UI components locally inside onCreate
        // findViewById links the XML components to Kotlin variables
        val cbJava = findViewById<CheckBox>(R.id.cbJava)
        val cbPython = findViewById<CheckBox>(R.id.cbPython)
        val cbKotlin = findViewById<CheckBox>(R.id.cbKotlin)
        val radioGroupGender = findViewById<RadioGroup>(R.id.radioGroupGender)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        // Set an OnClickListener on the Submit button
        // This method executes when the user clicks the button
        btnSubmit.setOnClickListener {
            // Logic for collecting information from CheckBoxes and RadioButtons
            // is written directly inside the click listener.

            // StringBuilder is used to efficiently create the result string
            val result = StringBuilder()

            // 1. Handling CheckBoxes (Skills)
            result.append("Selected Skills: ")
            var skillsFound = false
            
            // isChecked property returns true if the CheckBox is selected
            // We check each checkbox state individually
            if (cbJava.isChecked) {
                result.append("Java ")
                skillsFound = true
            }
            if (cbPython.isChecked) {
                result.append("Python ")
                skillsFound = true
            }
            if (cbKotlin.isChecked) {
                result.append("Kotlin ")
                skillsFound = true
            }
            
            // If no checkbox was checked, inform the user
            if (!skillsFound) {
                result.append("None")
            }

            result.append("\n\n") // Add new lines for formatting

            // 2. Handling RadioGroup (Gender)
            // checkedRadioButtonId returns the ID of the selected RadioButton in the group
            // If no radio button is selected, it returns -1
            val selectedGenderId = radioGroupGender.checkedRadioButtonId

            if (selectedGenderId != -1) {
                // Find the selected RadioButton using the ID retrieved from the group
                val selectedRadioButton: RadioButton = findViewById(selectedGenderId)
                // Get the text from the selected RadioButton
                val gender = selectedRadioButton.text
                result.append("Gender: $gender")
            } else {
                result.append("Gender: Not Selected")
            }

            // Display the final summary result in the TextView
            tvResult.text = result.toString()
        }
    }
}
