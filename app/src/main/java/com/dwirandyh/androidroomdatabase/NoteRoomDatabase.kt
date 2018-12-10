package com.dwirandyh.androidroomdatabase

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(Note::class), version = 1)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao


    companion object {
        private var INSTANCE: NoteRoomDatabase? = null

        fun getInstance(context: Context): NoteRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(NoteRoomDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        NoteRoomDatabase::class.java, "note.db")
                        .build()
                }
            }
            return INSTANCE
        }
    }

}