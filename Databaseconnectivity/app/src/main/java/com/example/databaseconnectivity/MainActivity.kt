package com.example.databaseconnectivity

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity: A simple student registration app using a single Kotlin file.
 * 
 * Why used: This class combines the UI logic and the Database helper class 
 * into one file to keep the project structure very simple for beginners.
 */
class MainActivity : AppCompatActivity() {

    // UI elements
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var btnInsert: Button
    private lateinit var btnRetrieve: Button
    private lateinit var tvDisplay: TextView

    // Database helper instance
    private lateinit var dbHelper: StudentDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        btnInsert = findViewById(R.id.btnInsert)
        btnRetrieve = findViewById(R.id.btnRetrieve)
        tvDisplay = findViewById(R.id.tvDisplay)

        // Initialize DB Helper
        dbHelper = StudentDbHelper(this)

        // Set click listeners for buttons
        btnInsert.setOnClickListener {
            insertData()
        }

        btnRetrieve.setOnClickListener {
            retrieveData()
        }
    }

    /**
     * Inserts data into the SQLite database.
     * When to use: When the 'Insert Data' button is clicked.
     */
    private fun insertData() {
        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()

        if (name.isNotEmpty() && email.isNotEmpty()) {
            val db = dbHelper.writableDatabase // Open for writing
            val values = ContentValues().apply {
                put("name", name)
                put("email", email)
            }
            val result = db.insert("students", null, values)

            if (result != -1L) {
                Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show()
                etName.text.clear()
                etEmail.text.clear()
            } else {
                Toast.makeText(this, "Failed to Save Data", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Retrieves data from the SQLite database.
     * When to use: When the 'Retrieve Data' button is clicked.
     */
    private fun retrieveData() {
        val db = dbHelper.readableDatabase // Open for reading
        val cursor = db.rawQuery("SELECT * FROM students", null)
        val resultText = StringBuilder()

        // Loop through all rows in the table
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val email = cursor.getString(2)
                resultText.append("ID: $id | Name: $name | Email: $email\n")
            } while (cursor.moveToNext())
        }
        cursor.close() // Close the cursor to free resources

        if (resultText.isNotEmpty()) {
            tvDisplay.text = resultText.toString()
        } else {
            tvDisplay.text = "No records found"
        }
    }

    /**
     * StudentDbHelper: A class to manage the SQLite database.
     * Why used: It handles table creation and versioning.
     */
    inner class StudentDbHelper(context: Context) : SQLiteOpenHelper(context, "StudentDatabase", null, 1) {
        
        // Method called when database is created for the first time
        override fun onCreate(db: SQLiteDatabase) {
            val createTable = "CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT)"
            db.execSQL(createTable)
        }

        // Method called when database is upgraded
        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS students")
            onCreate(db)
        }
    }
}