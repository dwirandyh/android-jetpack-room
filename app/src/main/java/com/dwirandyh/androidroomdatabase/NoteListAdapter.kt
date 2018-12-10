package com.dwirandyh.androidroomdatabase

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item.view.*

class NoteListAdapter(val context: Context) :
    RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)
    lateinit var items: List<Note>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView: View = layoutInflater.inflate(R.layout.list_item, parent, false)
        val viewHolder = NoteViewHolder(itemView)
        return viewHolder
    }


    override fun getItemCount(): Int {
        return if (!items.isEmpty()) {
            items.size
        } else {
            0
        }
    }

    fun setNotes(notes: List<Note>) {
        items = notes
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        if (items.isNotEmpty()) {
            holder.textViewNote.text = items.get(position).note
        } else {
            holder.textViewNote.text = "Note is empty"
        }
    }


    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewNote: TextView = view.textNote
    }
}