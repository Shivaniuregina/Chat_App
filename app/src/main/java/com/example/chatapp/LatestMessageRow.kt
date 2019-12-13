// Author: Shivani D. Shah
// Student ID: 200392733

//Package Used: com.example.chatapp
package com.example.chatapp

//Importing Libraries
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.latest_message_row.view.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase


//LatestMessageRow Class
class LatestMessageRow(val textmes: ChatMessage): Item<GroupieViewHolder>() {

    //To store the chat buddy.
    var chatbuddy: User? = null
    //Making use of getlayout function to return the layout
    override fun getLayout(): Int {
        return R.layout.latest_message_row
    }

    //bind function used to get the latest message between chat buddy
    override fun bind(storemes: GroupieViewHolder, position: Int) {
        storemes.itemView.message_textview_latest_message.text = textmes.text
        //To store the chat buddy id.
        val chatPartnerId: String
        if (textmes.fromId == FirebaseAuth.getInstance().uid) {
            chatPartnerId = textmes.toId
        } else {
            chatPartnerId = textmes.fromId
        }

        //Making reference
        val reference = FirebaseDatabase.getInstance().getReference("/users/$chatPartnerId")
        reference.addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {

            }
            //On data change update the values
            override fun onDataChange(p0: DataSnapshot) {
                chatbuddy = p0.getValue(User::class.java)
                storemes.itemView.username_textview_latest_message.text = chatbuddy?.username
                //get image and load image.
                val getimage = storemes.itemView.imageview_latest_message
                Picasso.get().load(chatbuddy?.profileImageUrl).into(getimage)
            }


        })
    }


}
