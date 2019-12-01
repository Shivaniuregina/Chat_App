package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle


import android.content.Intent

//import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            register.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()

            Log.d("MainActivity", "Email is: " + email)
            Log.d("MainActivity", "Password: $password")

            // Firebase Authentication to create a user with email and password
        }

        already_account.setOnClickListener {
            Log.d("MainActivity", "Try to show login activity")

            // launch the login activity somehow
            val intent = Intent(this, SigninActivity::class.java)
           startActivity(intent)
        }

    }
}
