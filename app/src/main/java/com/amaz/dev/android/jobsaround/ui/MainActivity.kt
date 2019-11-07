package com.amaz.dev.android.jobsaround.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amaz.dev.android.jobsaround.R

class MainActivity : AppCompatActivity() {


    companion object{
        fun start(context: Context){
            val intent = Intent(context , MainActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }


}