// Author: Shivani D. Shah
// Student ID: 200392733


//PACKAGE: chatapp
package com.example.chatapp

//Importing Libraries
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signin.*

//SigninActivity
class SigninActivity: AppCompatActivity() {

    //onCreate Function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signin)


        noaccount.setOnClickListener{
            finish()
        }

        //If sigin is pressed then perform Login
        signin.setOnClickListener {
            loginaccount()
        }
    }

    //Perform login into the account
    private fun loginaccount() {
        //Get email and password
        val email = username.text.toString()
        val password = password.text.toString()

        //If email and password is empty
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill the email and password.", Toast.LENGTH_SHORT).show()
            return
        }

        //Authenticate using Firebase
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                //Intent the activity.
                val changescreen = Intent(this, MessageActivity::class.java)
                changescreen.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(changescreen)
            }
            .addOnFailureListener {
                //Error if not able to log in.
                Toast.makeText(this, "Failed to log in: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

}


