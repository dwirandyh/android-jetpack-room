package com.dwirandyh.androidroomdatabase

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import android.util.Log

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = this.javaClass.simpleName

    var noteDao: NoteDao? = null
    var noteDB: NoteRoomDatabase? = null
    var mAllNotes: LiveData<List<Note>>? = null

    init {
        noteDB = NoteRoomDatabase.getInstance(application)
        noteDao = noteDB?.noteDao()

        mAllNotes = noteDao?.getAllNotes()
    }

    fun insert(note: Note) {
        InsertAsyncTask(noteDao).execute(note)
    }

    fun getAllNotes(): LiveData<List<Note>>? {
        return mAllNotes
    }


    class InsertAsyncTask(notedao: NoteDao?) : AsyncTask<Note, Void, Void>() {

        var mNoteDao: NoteDao? = null

        init {
            this.mNoteDao = notedao
        }

        override fun doInBackground(vararg params: Note?): Void? {
            mNoteDao?.insert(params[0])
            return null
        }

    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "ViewModel Destroyed")
    }
}