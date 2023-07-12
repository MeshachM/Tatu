package com.mparee.bakibz

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.ActionCodeEmailInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.mparee.bakibz.R
import kotlinx.android.synthetic.main.activity_choosegame.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_mainmedium.*
import kotlinx.android.synthetic.main.activity_startscreen.*
import kotlin.random.Random

class LoginActivity : AppCompatActivity() {
   private var mAuth:FirebaseAuth?=null

    private var database=FirebaseDatabase.getInstance()
    private var myRef=database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()
    }
    fun buLoginEvent(view:android.view.View){
        loginfx(eemail.text.toString(),ppassword.text.toString())
    }
        fun loginfx(email: String, Password:String) {
            mAuth!!.createUserWithEmailAndPassword(email,Password)
                .addOnCompleteListener(this){task ->
                    if (task.isSuccessful){
                        var currentUser =mAuth!!.currentUser
                        if(currentUser!=null) {
                            myRef.child("User").child(SplitString(currentUser.email.toString())).child("Request").setValue(currentUser.uid)
                        }
                        Toast.makeText(applicationContext,"Login Successful",Toast.LENGTH_LONG).show()
                        LoadMain()
                    }
                    else{
                        Toast.makeText(applicationContext,"Login Failed",Toast.LENGTH_LONG).show()
                    }
                }
        }

   override fun onStart() {
       super.onStart()
        LoadMain()
  }
         fun LoadMain(){
             var currentUser =mAuth!!.currentUser
             if(currentUser!=null) {
                 var intent = Intent(this, MainOnlineActivity::class.java)
                 intent.putExtra("email", currentUser.email)
                 intent.putExtra("uid", currentUser.uid)
                 startActivity(intent)
             }
             }

    fun SplitString(str:String):String{
        var split=str.split("@")
        return split[0]
    }
        }




