//Package used
package com.example.chatapp

//Importing Header files
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main.view.username
import kotlinx.android.synthetic.main.activity_new_message.*
import kotlinx.android.synthetic.main.activity_signin.view.*
import kotlinx.android.synthetic.main.user_new_message.view.*
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.database.DataSnapshot


//Class NewMessageActivity
class NewMessageActivity : AppCompatActivity() {


    //Companion object
    companion object {
        val USER_KEY = "USER_KEY"
    }

    //onCreate function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)

        supportActionBar?.title="Select User"
        //Get the users.
        getUsers()
    }


    //Get Users
    private fun getUsers() {
        //Get refernces
        val references = FirebaseDatabase.getInstance().getReference("/users")
        references.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            // ON DATA CHANGE UPDATE THE DATA
            override fun onDataChange(p0: DataSnapshot) {
                //Used Groupie libraray to get the View holders.
                val adapter = GroupAdapter<GroupieViewHolder>()

                p0.children.forEach {
                    //Get sll the child elements
                    val getuser = it.getValue(User::class.java)
                    //check the elemnt is not null
                    if (getuser != null) {
                        adapter.add(Users_data(getuser))
                    }

                }
                //When clicked on recycler view.
                adapter.setOnItemClickListener { item, view ->

                    val userItem = item as Users_data
                    //Intent and start new activity
                    val intent = Intent(view.context, ChatLogActivity::class.java)
                    intent.putExtra(USER_KEY, userItem.user)
                    startActivity(intent)

                    finish()

                }
                //Make changes to the recycler view in the XML file.
                recyclerview.adapter = adapter
            }


        })
    }
}

//Users_data class to store the user details
class Users_data(val user: User): Item<GroupieViewHolder>() {


    override fun getLayout(): Int {
        return R.layout.user_new_message
    }

    override fun bind(holder: GroupieViewHolder, position: Int) {
        holder.itemView.textView.text = user.username
        Picasso.get().load(user.profileImageUrl).into(holder.itemView.imageView)
    }




}

