package com.example.mytodoapp

import Task
import TaskAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    //mon tableau de tache
    var taskList : MutableList<Task> = mutableListOf()

    companion object{
        private const val TASK_LIST_KEY = "taskList"}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //utiliser pour restaurer l'etat de l'activite
        if(savedInstanceState != null)
        {
            taskList = savedInstanceState.getParcelableArrayList<Task>(TASK_LIST_KEY)?.toMutableList()!!
        }
        //verifie si l'activite a ete demarrer avec un intent
        if (intent != null) {
            val task = Task(intent.getStringExtra("EXTRA_TITLE" )?: "",intent.getStringExtra("EXTRA_DESC") ?: "")
            taskList.add(task)
            Log.d("1", taskList.size.toString())

        }
        val addtask : FloatingActionButton = findViewById<FloatingActionButton>(R.id.btnAdd)

        addtask.setOnClickListener {
            val intent = Intent(this,addTaskActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    //utiliser pour enregister l'etat de l'activite
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(TASK_LIST_KEY, ArrayList(taskList))
        super.onSaveInstanceState(outState)
    }
    override fun onStart() {
        super.onStart()
        val listViewtask = findViewById<ListView>(R.id.listView)
        val adapter = TaskAdapter(this,R.layout.activity_item_task,taskList)
        //utiliser pour informer l'adapteur de la modification du tableau
        adapter.notifyDataSetChanged()
        listViewtask.adapter = adapter
    }
}