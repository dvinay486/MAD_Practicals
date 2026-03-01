/**
 * MSBTE Course: 316006 - Mobile Application Development
 * Subject: Date and Time Picker Implementation
 * 
 * Description:
 * This Activity demonstrates how to use DatePickerDialog and TimePickerDialog
 * to accept date and time input from the user in an Android Application.
 * 
 * DatePicker: A widget for selecting a date (day, month, year).
 * TimePicker: A widget for selecting a time (hour, minute).
 */
package com.example.datetimepicker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import java.util.Locale

/**
 * MainActivity class inheriting from AppCompatActivity.
 * This class serves as the entry point of our application.
 */
class MainActivity : AppCompatActivity() {

    // 1. Declaring UI variables to hold references to our layout components
    private lateinit var btnDatePicker: Button
    private lateinit var btnTimePicker: Button
    private lateinit var tvSelectedDate: TextView
    private lateinit var tvSelectedTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 2. Setting the layout for this Activity from activity_main.xml
        setContentView(R.layout.activity_main)

        // 3. Initializing UI components using findViewById
        // This links our Kotlin code with the XML IDs
        btnDatePicker = findViewById(R.id.btnDatePicker)
        btnTimePicker = findViewById(R.id.btnTimePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvSelectedTime = findViewById(R.id.tvSelectedTime)

        // 4. Setting a click listener on the "Select Date" button
        // When the button is clicked, showDatePicker() function is called
        btnDatePicker.setOnClickListener {
            showDatePicker()
        }

        // 5. Setting a click listener on the "Select Time" button
        // When the button is clicked, showTimePicker() function is called
        btnTimePicker.setOnClickListener {
            showTimePicker()
        }
    }

    /**
     * Method to display the DatePickerDialog.
     * This uses the DatePickerDialog class provided by the Android framework.
     */
    private fun showDatePicker() {
        // Fetching the current date using the Calendar class to show as default
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        /**
         * DatePickerDialog Constructor:
         * Parameters: (Context, OnDateSetListener, Year, Month, Day)
         * 
         * The listener (lambda) is triggered when the user clicks 'OK' after selecting a date.
         */
        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                /**
                 * Note: Months are indexed from 0 to 11 in Android.
                 * January = 0, February = 1, ..., December = 11.
                 * So we add 1 to 'selectedMonth' to show it correctly to the user (1-12).
                 */
                val dateString = "Selected Date: $selectedDay/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate.text = dateString
            },
            year,
            month,
            day
        )
        
        // Calling show() to display the dialog on the screen
        datePickerDialog.show()
    }

    /**
     * Method to display the TimePickerDialog.
     * This uses the TimePickerDialog class.
     */
    private fun showTimePicker() {
        // Fetching the current time using the Calendar class to show as default
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        /**
         * TimePickerDialog Constructor:
         * Parameters: (Context, OnTimeSetListener, Hour, Minute, is24HourView)
         * 
         * is24HourView: If true, shows 24-hour format; if false, shows AM/PM.
         */
        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                // Using String.format with Locale.getDefault() for proper formatting (e.g., 09:05)
                val timeString = String.format(Locale.getDefault(), "Selected Time: %02d:%02d", selectedHour, selectedMinute)
                tvSelectedTime.text = timeString
            },
            hour,
            minute,
            true // We are using 24-hour format here
        )
        
        // Calling show() to display the dialog
        timePickerDialog.show()
    }
}
