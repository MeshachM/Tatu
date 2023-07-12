package com.mparee.bakibz

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.mparee.bakibz.R
import kotlinx.android.synthetic.main.activity_choosegame.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_mainmedium.*
import kotlinx.android.synthetic.main.activity_startscreen.*
import kotlin.random.Random

class ChooseGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choosegame)
        simplebtn.setOnClickListener {
            val intent = Intent(this, MainPlayActivity::class.java)
            startActivity(intent)
        }
        /*mediumbtn.setOnClickListener {
            val intent = Intent(this, MainMediumActivity::class.java)
            startActivity(intent)
        }*/
        complexbtn.setOnClickListener {
            val intent = Intent(this,MainPlayComplexActivity::class.java)
            startActivity(intent)
        }

        }
    }



