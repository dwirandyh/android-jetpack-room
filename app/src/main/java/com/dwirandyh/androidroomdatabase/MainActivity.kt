package com.dwirandyh.androidroomdatabase

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.name;
    val NEW_NOTE_ACTIVITY_REQUEST_CODE = 100

    lateinit var noteViewModel: NoteViewModel
    lateinit var noteListAdapter: NoteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addNote.setOnClickListener {
            val intent = Intent(this@MainActivity, NewNoteActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        }

        noteListAdapter = NoteListAdapter(this)
        recyclerview.adapter = noteListAdapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        noteViewModel = ViewModelProviders.of(this@MainActivity).get(NoteViewModel::class.java)

        noteViewModel.getAllNotes()?.observe(this, Observer<List<Note>> { resources ->
            if (resources != null) {
                noteListAdapter.setNotes(resources)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            var noteText = data?.getStringExtra(NewNoteActivity.NOTE_ADDED)
            var note = Note(null, noteText)
            noteViewModel.insert(note)

            Toast.makeText(applicationContext, "New Note Saved", Toast.LENGTH_SHORT).show()


        } else {
            Toast.makeText(applicationContext, "Failed to save note", Toast.LENGTH_SHORT).show()
        }
    }
}
