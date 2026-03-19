package com.example.emailappdemo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var to: EditText
    lateinit var subject: EditText
    lateinit var message: EditText
    lateinit var send: Button
    lateinit var receive: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        to = findViewById(R.id.etTo)
        subject = findViewById(R.id.etSubject)
        message = findViewById(R.id.etMessage)
        send = findViewById(R.id.btnSend)
        receive = findViewById(R.id.btnReceive)

        // Send Email
        send.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "message/rfc822"

            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to.text.toString()))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject.text.toString())
            intent.putExtra(Intent.EXTRA_TEXT, message.text.toString())

            startActivity(Intent.createChooser(intent, "Send Email"))
        }

        // Receive Email (Open Inbox)
        receive.setOnClickListener {

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("mailto:")

            startActivity(intent)
        }
    }
}