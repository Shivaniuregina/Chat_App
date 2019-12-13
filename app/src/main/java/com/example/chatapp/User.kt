// Author: Shivani D. Shah
// Student ID: 200392733

//Package
package com.example.chatapp
//Importing reuired libararies
import androidx.appcompat.app.AppCompatActivity
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import android.os.Bundle

//Creating the user class

@Parcelize
class User(val uid: String, val username: String, val profileImageUrl: String): Parcelable {
    constructor() : this("", "", "")
    //initializing uid -> "",username->"",profileImageUrl->""
}
