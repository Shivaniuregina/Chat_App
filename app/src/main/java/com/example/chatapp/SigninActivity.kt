package com.example.chatapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signin)

        signin.setOnClickListener {
            val email = username.text.toString()
            val password = password.text.toString()

            Log.d("Login", "Attempt login with email/pw: $email/***")
        }

        noaccount.setOnClickListener{
            finish()
        }
    }

}