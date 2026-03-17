package com.example.smsapplication

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * MainActivity: The main entry point of our application.
 * This class inherits from AppCompatActivity, which provides backward compatibility 
 * for modern Android features on older devices.
 */
class MainActivity : AppCompatActivity() {

    // Declare UI elements as member variables to be accessed throughout the class.
    private lateinit var etPhoneNumber: EditText
    private lateinit var etMessage: EditText
    private lateinit var btnSend: Button

    // A constant code to identify our permission request.
    private val SMS_PERMISSION_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Theory: setContentView links the Kotlin code to the XML layout (activity_main.xml).
        setContentView(R.layout.activity_main)

        // Initialize UI components by finding them in the XML layout using their IDs.
        etPhoneNumber = findViewById(R.id.etPhoneNumber)
        etMessage = findViewById(R.id.etMessage)
        btnSend = findViewById(R.id.btnSend)

        /**
         * setOnClickListener: This method listens for a click event on the button.
         * When the user taps the 'Send' button, the code inside this block will run.
         */
        btnSend.setOnClickListener {
            val phoneNumber = etPhoneNumber.text.toString().trim()
            val message = etMessage.text.toString().trim()

            // Validate inputs: Ensure neither field is empty before attempting to send.
            if (phoneNumber.isNotEmpty() && message.isNotEmpty()) {
                // Check if the app already has permission to send SMS.
                if (checkPermission(Manifest.permission.SEND_SMS)) {
                    sendSMS(phoneNumber, message)
                } else {
                    // If not, request permission from the user.
                    requestRuntimePermission()
                }
            } else {
                Toast.makeText(this, "Please enter both phone number and message", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * checkPermission: A helper function to verify if a specific permission is granted.
     * ContextCompat.checkSelfPermission returns PERMISSION_GRANTED if allowed.
     */
    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * requestRuntimePermission: Since Android 6.0 (Marshmallow), apps must request 
     * sensitive permissions at runtime, not just in the manifest.
     */
    private fun requestRuntimePermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.SEND_SMS),
            SMS_PERMISSION_CODE
        )
    }

    /**
     * onRequestPermissionsResult: This callback method is triggered after the user 
     * responds to the permission request dialog.
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted by user.
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                // Permission denied by user.
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * sendSMS: The core function that uses the Android Telephony API to send the message.
     * Theory: SmsManager is the system service used to manage SMS operations.
     */
    private fun sendSMS(phoneNumber: String, message: String) {
        try {
            // Get the default instance of SmsManager.
            val smsManager: SmsManager = this.getSystemService(SmsManager::class.java)
            
            // sendTextMessage: Sends a raw text based SMS.
            // Arguments: recipient address, service center (null for default), message body, 
            // sent intent (to track status), and delivery intent.
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            
            Toast.makeText(this, "SMS Sent Successfully!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to send SMS: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}
