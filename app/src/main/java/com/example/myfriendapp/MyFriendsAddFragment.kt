package com.example.myfriendapp

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.my_friends_add_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyFriendsAddFragment :Fragment(){
    companion object {
        fun newInstance() : MyFriendsAddFragment{
            return MyFriendsAddFragment()
        }
    }

    private var namaInput: String = ""
    private var emailInput: String = ""
    private var telpInput: String = ""
    private var alamatInput: String = ""
    private var genderInput: String = ""

    private var db: AppDatabase? = null
    private var myFriendDao: MyFriendDAO? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.my_friends_add_fragment,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLocalDB()
        initView()
    }

    private fun initView() {
        btnSave.setOnClickListener {
            validasiInput()
        }
    }

    private fun initLocalDB(){
        db = AppDatabase.getAppDatabase(activity!!)
        myFriendDao= db?.myFriendDao()
    }

    private fun validasiInput(){
        namaInput = edit_name.text.toString()
        emailInput = Email.text.toString()
        telpInput = Telp.text.toString()
        alamatInput = Alamat.text.toString()
        genderInput = spinner_gender.selectedItem.toString()

        when{
            namaInput.isEmpty() -> edit_name.error = "Nama tidak boleh kosong"
            genderInput.equals("Pilih Kelamin") -> tampilToast("Pilih gender!")
            emailInput.isEmpty() -> Email.error = "Email tidak boleh kosong"
            telpInput.isEmpty() -> Telp.error = "Telepon tidak boleh kosong"
            alamatInput.isEmpty() -> Alamat.error = "Alamat tidak boleh kosong"

            else -> {
                val teman = MyFriend(null, namaInput, genderInput, emailInput, telpInput, alamatInput)

                tambahDataTeman(teman)
            }
        }
    }

    private fun tambahDataTeman(teman:MyFriend): Job{
        return GlobalScope.launch {
            myFriendDao?.tambahTeman(teman)
            (activity as MainActivity).tampilMyFriendsFragment()
        }
    }

    private fun tampilToast(message: String){
        Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

