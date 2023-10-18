package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_start : Button = findViewById(R.id.btn_start)
        val et_text : EditText = findViewById(R.id.et_name)
        btn_start.setOnClickListener {
            if(et_text.text.isEmpty()){
                Toast.makeText(this, "Name can't be empty!", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent: Intent = Intent(this, QuizQuestionsActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}