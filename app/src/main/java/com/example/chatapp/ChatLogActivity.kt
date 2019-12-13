// Author: Shivani D. Shah
// Student ID: 200392733

// Chat app package
package com.example.chatapp
//Header Files Used
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_chat_log.*
import kotlinx.android.synthetic.main.chat_from_row.view.*
import kotlinx.android.synthetic.main.chat_to_row.view.*
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item


//Class ChatLogActivity to keep track of the logs of the messages send and receive.
class ChatLogActivity : AppCompatActivity() {

    //An companion object just to recover from the error.
    companion object {
        val TAG = "ChatLog"
    }

    //Initializing user to null.
    var user_info: User? = null

    // Making use of Group View Holder to hold different groups of messages.
    val adapter = GroupAdapter<GroupieViewHolder>()


    //onCreate function.
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        recyclerview_chat_log.adapter = adapter
        //Extrct user information using the user key.
        user_info = intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)
        //Log.d(TAG, user_info)
        supportActionBar?.title = user_info?.username
        //Listen for any new messages.
        trackmessage()

        //When send button is pressed then send the message.
        send_button_chat_log.setOnClickListener{
            messagesend()
        }
    }

    //Track messages
    private fun trackmessage()
    {
        //Firebase data gathering
        val toId = user_info?.uid
        val fromId = FirebaseAuth.getInstance().uid
        //Get the reference from and to the user ids.
        val refers = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId")
        Log.d(TAG, fromId)

        refers.addChildEventListener(object: ChildEventListener {

            //Overriding functions to change their definitions
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            }


            override fun onChildAdded(p0: DataSnapshot, p1: String?) {

                //Getting the childrens data from Firebase.
                val chatMessage = p0.getValue(ChatMessage::class.java)
                //Checking if their is a chat message or not.
                if (chatMessage != null) {

                    if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {
                        val currentUser = MessageActivity.currentUser ?: return
                        Log.d(TAG, chatMessage.text)
                        adapter.add(ChatFromItem(chatMessage.text, currentUser))
                    } else {
                        Log.d(TAG, chatMessage.text)
                        adapter.add(ChatToItem(chatMessage.text, user_info!!))
                    }
                }
                //Adding it to the chat log in the recycler view.
                recyclerview_chat_log.scrollToPosition(adapter.itemCount - 1)
            }



            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }

        })

    }

    //Function to send message.
    private fun messagesend() {

        val text = edittext_chat_log.text.toString()

        val fromId = FirebaseAuth.getInstance().uid
        val user = intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)
        val toId = user.uid
        //Send message to Firebase.
        if (fromId == null) return
        val from = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId").push()

        val to = FirebaseDatabase.getInstance().getReference("/user-messages/$toId/$fromId").push()

        val message_chat = ChatMessage(from.key!!, text, fromId, toId, System.currentTimeMillis() / 1000)

        from.setValue(message_chat)
            .addOnSuccessListener {
                //clear the chat log
                edittext_chat_log.text.clear()
                //update the recycler view.
                recyclerview_chat_log.scrollToPosition(adapter.itemCount - 1)
            }
        //Set the to value.
        to.setValue(message_chat)

        // store fromid to toId.
        val store_message = FirebaseDatabase.getInstance().getReference("/latest-messages/$fromId/$toId")
        store_message.setValue(message_chat)

        // store tomid to FromId.
        val latestMessageToRef = FirebaseDatabase.getInstance().getReference("/latest-messages/$toId/$fromId")
        latestMessageToRef.setValue(message_chat)
    }
}


// Sending message to other users.
class ChatToItem(val message: String, val user: User): Item<GroupieViewHolder>() {

    // Overriding function defition of getLayout()
    override fun getLayout(): Int {

        return R.layout.chat_to_row
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.textview_to_row.text = message
        val pic = user.profileImageUrl
        val image_view = viewHolder.itemView.imageview_chat_to_row
        //Getting th image using Picasso
        Picasso.get().load(pic).into(image_view)
    }


}

//Receiving message from other user.
class ChatFromItem(val message: String, val user: User): Item<GroupieViewHolder>() {

    // Overriding function defition of getLayout()
    override fun getLayout(): Int {

        return R.layout.chat_from_row
    }

    //Overriding bind function
    override fun bind(Holders: GroupieViewHolder, position: Int) {


        val profile_image = user.profileImageUrl
        Holders.itemView.textview_from_row.text = message

        //Getting the image
        val image_view = Holders.itemView.imageview_chat_from_row
        //Loading th image using Picasso
        Picasso.get().load(profile_image).into(image_view)

    }
}



