package com.dwirandyh.androidroomdatabase

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note?)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): LiveData<List<Note>>
}