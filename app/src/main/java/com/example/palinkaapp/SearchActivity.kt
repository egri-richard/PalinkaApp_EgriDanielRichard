package com.example.palinkaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SearchActivity : AppCompatActivity() {
    private lateinit var fozoQuery: EditText
    private lateinit var gyumolcsQuery: EditText
    private lateinit var palinkaQueryBtn: Button
    private lateinit var backFromQueryBtn: Button
    private lateinit var queryResultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        init()

        palinkaQueryBtn.setOnClickListener {
            if (fozoQuery.text.isNotEmpty() && gyumolcsQuery.text.isNotEmpty()) {
                val db = DBHandler(this)
                val alkString = db.query(fozoQuery.text.toString(), gyumolcsQuery.text.toString())
                queryResultText.text = alkString
            } else {
                Toast.makeText(applicationContext, "Kérem töltse ki az összes mezőt!", Toast.LENGTH_SHORT).show()
            }
        }

        backFromQueryBtn.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }

    private fun init() {
        fozoQuery = findViewById(R.id.fozoQuery)
        gyumolcsQuery = findViewById(R.id.gyumolcsQuery)
        palinkaQueryBtn = findViewById(R.id.palinkaQueryBtn)
        backFromQueryBtn = findViewById(R.id.backFromQueryBtn)
        queryResultText = findViewById(R.id.queryResultText)
    }
}