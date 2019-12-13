// Author: Shivani D. Shah
// Created: November 28th 2019
// Student ID: 200392733

//Package used
package com.example.chatapp

//Libraries Used
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast

//Main Activity Class
class MainActivity : AppCompatActivity() {
    //onCreate function.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //If the user already have account then redirect user to signin page.
        already_account.setOnClickListener {

            val signin = Intent(this, SigninActivity::class.java)
            startActivity(signin)

        }
        //When users clicks on selectphoto image view.
        selectphoto.setOnClickListener {
            //OPENS UP THE GALLERY OF THE ANDROID DEVICE
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        //When users click the registes button
        register.setOnClickListener {
            //Get email and password
            val email = email.text.toString()
            val password = password.text.toString()

            //Check if email is empty or password is empty
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email or password cannot be blank", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Authenticate user using Firebase
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener
                    storeimageFirebase()
                }
                .addOnFailureListener{
                    Toast.makeText(this, "Failed to create new user: ${it.message}", Toast.LENGTH_SHORT).show()
                }
            //If authenticated then save user to Database
            // saveuser(it.toString())
        }


    }

    var photoused: Uri? = null


    //Function to save user to Firebase
    private fun saveuser(image: String) {

        //Get the Firebase instance
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val reference = FirebaseDatabase.getInstance().getReference("/users/$uid")
        val user = User(uid, username.text.toString(), image)
        reference.setValue(user)
            .addOnSuccessListener {
                //Set the intent and start the activity
                val intent = Intent(this, MessageActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            .addOnFailureListener {
                //print an error message
            }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {

            photoused = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, photoused)
            //photo_imageview.setImageBitmap(bitmap)
            //photo_imageview.alpha = 0f

            val bitmapDrawable = BitmapDrawable(bitmap)
            photo_imageview.alpha = 0f
            selectphoto.setBackgroundDrawable(bitmapDrawable)
            selectphoto.text=""
            photo_imageview.setBackgroundDrawable(bitmapDrawable)

            //val bitmapDrawable = BitmapDrawable(bitmap)
            //photo_imageview.setImsetBackgroundDrawable(bitmapDrawable)
        }
    }


    //store image to firebase
    private fun storeimageFirebase() {

        if (photoused == null) return

        val filename = UUID.randomUUID().toString()
        val refers = FirebaseStorage.getInstance().getReference("/images/$filename")

        refers.putFile(photoused!!)
            .addOnSuccessListener {


                refers.downloadUrl.addOnSuccessListener {

                    // i have commneted it
                    //now the name is saveuser
                    saveuser(it.toString())
                }
            }
            // .addOnFailureListener()
            .addOnFailureListener {
                //display an error message
                Log.d("MainActivity", "Cannot upload: ${it.message}")
            }
    }




}

