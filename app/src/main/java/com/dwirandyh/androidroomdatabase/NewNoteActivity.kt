package com.dwirandyh.androidroomdatabase

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_new_note.*

class NewNoteActivity : AppCompatActivity() {

    companion object {
        val NOTE_ADDED = "note_added"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)


        btnSave.setOnClickListener {
            var resultIntent = Intent()

            if (TextUtils.isEmpty(editTextNote.text)) {
                setResult(Activity.RESULT_CANCELED, resultIntent)
            } else {
                var note = editTextNote.text.toString();
                resultIntent.putExtra(NOTE_ADDED, note)
                setResult(RESULT_OK, resultIntent)
            }

            finish()
        }
    }
}
