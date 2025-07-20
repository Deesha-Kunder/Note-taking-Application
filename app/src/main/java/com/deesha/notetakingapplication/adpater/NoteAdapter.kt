package com.deesha.notetakingapplication.adpater

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.deesha.notetakingapplication.databinding.NoteLayoutBinding
import com.deesha.notetakingapplication.fragment.HomeFragmentDirections
import com.deesha.notetakingapplication.room.Note
import java.util.Random

class NoteAdapter(): RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {


    class MyViewHolder(val binding:NoteLayoutBinding):RecyclerView.ViewHolder(binding.root)
        private val diffCallBack = object: DiffUtil.ItemCallback<Note>(){
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id && oldItem.title == newItem.title && oldItem.body ==newItem.body
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem==newItem
            }

        }
    val differ = AsyncListDiffer(this,diffCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(NoteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentNote = differ.currentList[position]
        holder.binding.titleNoteLayout.text = currentNote.title
        holder.binding.bodyNoteLayout.text = currentNote.body

        val random = Random()
        var color = Color.argb(255,
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256)
        )
        holder.binding.viewColor.setBackgroundColor(color)
        holder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToUpdateNoteFragment(currentNote)
            it.findNavController().navigate(direction)

        }


    }
}