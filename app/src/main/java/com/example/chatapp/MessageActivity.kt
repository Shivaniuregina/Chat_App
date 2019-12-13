// Author: Shivani D. Shah
// Student ID: 200392733

//package : chatapp
package com.example.chatapp

//Importing Libraries
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_message.*
import kotlinx.android.synthetic.main.latest_message_row.view.*
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


//class MessageActivity
class MessageActivity : AppCompatActivity() {

    companion object {
        var currentUser: User? = null
        val TAG = "LatestMessages"
    }

    val store_messages = HashMap<String, ChatMessage>()

    //onCreate function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        recyclerview_latest_messages.adapter = adapter
        recyclerview_latest_messages.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        // set item click listener on your adapter
        adapter.setOnItemClickListener { item, view ->
            Log.d(TAG, "123")
            val intent = Intent(this, ChatLogActivity::class.java)

            val row = item as LatestMessageRow
            intent.putExtra(NewMessageActivity.USER_KEY, row.chatbuddy)
            startActivity(intent)
        }

        //This function tracks any updates whenver any new messages arrives
        track_messages()
        //Fetch details of the current user.
        getUser()
        //Authenticate  that user is logged in
        authenticateuser()
    }


    //Upadte any changes in the messages
    private fun update_messages() {
        adapter.clear()
        store_messages.values.forEach {
            adapter.add(LatestMessageRow(it))
        }
    }

    //Track messages
    private fun track_messages() {
        val fromId = FirebaseAuth.getInstance().uid
        //Get reference from the latest messages
        val refers = FirebaseDatabase.getInstance().getReference("/latest-messages/$fromId")

        //Add the event Listener tot track for updates
        refers.addChildEventListener(object: ChildEventListener {


            //IF child is changed
            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                //Get the chat message and store it.
                val chat_store = p0.getValue(ChatMessage::class.java) ?: return
                store_messages[p0.key!!] = chat_store
                update_messages()
            }


            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }
            override fun onChildRemoved(p0: DataSnapshot) {

            }
            //If child is added
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chat_store = p0.getValue(ChatMessage::class.java) ?: return
                store_messages[p0.key!!] = chat_store
                update_messages()
            }


            override fun onCancelled(p0: DatabaseError) {

            }


        })
    }

    //Groupie library used to fix the adapter
    val adapter = GroupAdapter<GroupieViewHolder>()

    //Authenticate users
    private fun authenticateuser() {
        //Get uid from Firebase
        val uid = FirebaseAuth.getInstance().uid
        //Check if it is null or not
        if (uid == null) {

            val screen = Intent(this, MainActivity::class.java)
            screen.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(screen)
        }
    }

    //Get users details
    private fun getUser() {
        val uid = FirebaseAuth.getInstance().uid
        val references = FirebaseDatabase.getInstance().getReference("/users/$uid")
        references.addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                currentUser = p0.getValue(User::class.java)
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_new_message -> {
                val intent = Intent(this, NewMessageActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_sign_out -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }


}










