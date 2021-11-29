package com.example.palinkaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var addBtn: Button
    private lateinit var searchBtn: Button
    private lateinit var listBtn: Button
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        addBtn.setOnClickListener {
            startActivity(Intent(applicationContext, InsertActivity::class.java))
            finish()
        }

        searchBtn.setOnClickListener {
            startActivity(Intent(applicationContext, SearchActivity::class.java))
            finish()
        }

        listBtn.setOnClickListener {
            val db = DBHandler(this)
            val data = db.listPalinka()
            resultText.text = ""
            for (p in data) {
                resultText.append(  "\nID: " + p.id.toString() +
                                    "\n Főző: " + p.fozo +
                                    "\n Gyümölcs: " + p.gyumolcs +
                                    "\n Alkoholtartalom: " + p.alkohol.toString() + " %\n")
            }
        }
    }

    private fun init() {
        addBtn = findViewById(R.id.addBtn)
        searchBtn = findViewById(R.id.searchBtn)
        listBtn = findViewById(R.id.listBtn)
        resultText = findViewById(R.id.resultText)
    }
}