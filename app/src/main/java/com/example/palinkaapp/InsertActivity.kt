package com.example.palinkaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class InsertActivity : AppCompatActivity() {
    private lateinit var insert_btn: Button
    private lateinit var back_btn: Button
    private lateinit var fozoIn: EditText
    private lateinit var gyumolcsIn: EditText
    private lateinit var alkoholIn: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
        init()

        insert_btn.setOnClickListener {
            if (fozoIn.text.isNotEmpty() && gyumolcsIn.text.isNotEmpty() && alkoholIn.text.isNotEmpty()) {
                val p = Palinka(fozoIn.text.toString(), gyumolcsIn.text.toString(), alkoholIn.text.toString().toInt())
                val db = DBHandler(this)
                db.insert(p)
            } else {
                Toast.makeText(applicationContext, "Kérem töltse ki az összes mezőt!", Toast.LENGTH_SHORT).show()
            }
        }

        back_btn.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }

    }

    private fun init() {
        insert_btn = findViewById(R.id.insertBtn)
        back_btn = findViewById(R.id.backBtn)
        fozoIn = findViewById(R.id.fozoIn)
        gyumolcsIn = findViewById(R.id.gyumolcsIn)
        alkoholIn = findViewById(R.id.alkoholIn)
    }
}