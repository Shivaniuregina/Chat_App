// Author: Shivani D. Shah
// Student ID: 200392733

//Package chatapp
package com.example.chatapp

//Imported classes
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


//Making a class ChatMessage to store the the message and the location of the chat message.
class ChatMessage(val id: String, val text: String, val fromId: String, val toId: String, val timestamp: Long)
{
    //Calling the constructor
    //Initialise the id,text,fromId,toId to empty and timestamp to -1.
    constructor() : this("", "", "", "", -1)
}
