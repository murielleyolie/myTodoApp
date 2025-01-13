package com.example.mytodoapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility

class addTaskActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_task)

        val btnSave : Button = findViewById<Button>(R.id.save)
        val btnCancel : Button = findViewById<Button>(R.id.delete)
        val titleView = findViewById<EditText>(R.id.taskname)
        val descriptionView = findViewById<EditText>(R.id.taskMultiLine)
        val error = findViewById<TextView>(R.id.error)
        var title: String
        var description : String
        btnSave.setOnClickListener {
            error.visibility = View.GONE
            title = titleView.text.toString()
            description = descriptionView.text.toString()
            val intent = Intent(this,MainActivity::class.java)
            if(title.isNotEmpty() && description.isNotEmpty())
            {
                intent.putExtra("EXTRA_TITLE", title)
                intent.putExtra("EXTRA_DESC", description)
                startActivity(intent)
                finish()
            }else{
                error.text = "Veuillez entrez toutes les informations"
                error.visibility = View.VISIBLE
            }
        }
        btnCancel.setOnClickListener {
            val edittext = HtmlCompat.fromHtml("", HtmlCompat.FROM_HTML_MODE_LEGACY) as Editable?
            titleView.text = edittext
            descriptionView.text = edittext
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}