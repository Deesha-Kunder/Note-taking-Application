package com.deesha.notetakingapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.navigation.findNavController
import com.deesha.notetakingapplication.MainActivity
import com.deesha.notetakingapplication.R
import com.deesha.notetakingapplication.adpater.NoteAdapter
import com.deesha.notetakingapplication.databinding.FragmentNewNoteBinding
import com.deesha.notetakingapplication.room.Note
import com.deesha.notetakingapplication.viewmodel.MyViewModel

class NewNoteFragment : Fragment(R.layout.fragment_new_note) {
private var _binding :FragmentNewNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var myViewModel: MyViewModel
    private lateinit var myView:View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myViewModel = (activity as MainActivity).viewModel
        myView = view
    }
    private fun saveNote(view:View){
        val title = binding.titleNewNote.text.toString().trim()
        val body = binding.bodyNewNote.text.toString().trim()
        if(title.isNotEmpty()){
            val note = Note(0,title,body)
            myViewModel.addNote(note)
            Toast.makeText(context,"Nate saved successfully",Toast.LENGTH_SHORT).show()
            view.findNavController().navigate(R.id.action_newNoteFragment_to_homeFragment)

        }
        else{
            Toast.makeText(context,"Please enter title of note",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        menu.clear()
        inflater.inflate(R.menu.newnote_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save_newNote->{saveNote(myView)}
        }
        return super.onOptionsItemSelected(item)
    }


}