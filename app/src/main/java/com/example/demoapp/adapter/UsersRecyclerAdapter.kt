package com.example.demoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.model.User
import kotlinx.android.synthetic.main.user_account_list.view.*

class UsersRecyclerAdapter (private val listUsers: List<User>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // inflating recycler item view
        return UserviewHolder(

            LayoutInflater.from(parent.context).inflate(R.layout.user_account_list, parent, false)
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserviewHolder -> {
                holder.bind(listUsers.get(position))
            }
        }
    }
    override fun getItemCount(): Int {
        return listUsers.size
    }
    /**
     * ViewHolder class
     */
    inner class UserviewHolder constructor(itemView: View ):RecyclerView.ViewHolder(itemView){
        val textname = itemView.txtv_firstnamelist
        val textlastname = itemView.txtv_lastnamelist
        val textemail= itemView.txtv_emailList
        val textpaswrd = itemView.txtv_paswrdlist

        fun bind(user: User){
            textname.setText(user.Firstname)
            textlastname.setText(user.Lastname)
            textemail.setText(user.email)
            textpaswrd.setText(user.password)


        }

    }


}