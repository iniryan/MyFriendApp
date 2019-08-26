package com.example.myfriendapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.my_friends_fragment.*

class MyFriendsFragment:Fragment(){

     private var listTeman: MutableList<MyFriend>? = null
     private var db:AppDatabase? = null
     private var myFriendDao: MyFriendDAO? = null

    companion object {
        fun newInstance() : MyFriendsFragment{
            return MyFriendsFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.my_friends_fragment,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLocalDb()
        initView()


    }

    private fun initLocalDb(){
        db = AppDatabase.getAppDatabase(activity!!)
        myFriendDao = db?.myFriendDao()
    }

    private fun initView() {
        fabAddfriend.setOnClickListener {
            (activity as MainActivity).tampilMyFriendsAddFragment()
        }
        //        simulasiDataTeman()
        //        tampilTeman()
        ambilDataTeman()
    }

    private fun ambilDataTeman(){
        listTeman = ArrayList()
        myFriendDao?.ambilSemuaTeman()?.observe(this, Observer { r ->

            listTeman = r as MutableList<MyFriend>?

            when{
                listTeman?.size == 0 -> tampilToast("Belum ada teman")

                else -> {
                    tampilTeman()
                }
            }
        })
    }



    private fun tampilToast(message: String){
        Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show()
    }



   // private fun simulasiDataTeman(){
   //     listTeman = ArrayList()

     //   (listTeman as ArrayList<MyFriend>).add(MyFriend(null,"lolo","L","lol@gmail.com","11111", "lalalala"))
       // (listTeman as ArrayList<MyFriend>).add(MyFriend(null,"lolo","L","lol@gmail.com","22222", "lalalala"))
    //}

    private fun tampilTeman(){

        listmyfriend.layoutManager = LinearLayoutManager(activity)
        listmyfriend.adapter = MyFriendAdaptor(activity!!, listTeman as ArrayList<MyFriend>)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}



