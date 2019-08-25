package com.example.myfriendapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.my_friends_item.*

class MyFriendAdaptor (private val context: Context, private val item: ArrayList<MyFriend>):
        RecyclerView.Adapter<MyFriendAdaptor.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.my_friends_item, parent, false))

    override fun onBindViewHolder(holder: MyFriendAdaptor.ViewHolder, position: Int) {
        holder.bindItem(item.get(position))
    }

    override fun getItemCount(): Int = item.size

    class ViewHolder (override val containerView: View):
            RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bindItem(item: MyFriend){
            txtFriendsName.text = item.nama
            txtFriendsEmail.text = item.email
            txtFriendsTlp.text = item.telp
        }
    }
}