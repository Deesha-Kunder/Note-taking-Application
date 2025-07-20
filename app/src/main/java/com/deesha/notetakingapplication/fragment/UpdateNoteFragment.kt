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
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.deesha.notetakingapplication.MainActivity
import com.deesha.notetakingapplication.R
import com.deesha.notetakingapplication.databinding.FragmentUpdateNoteBinding
import com.deesha.notetakingapplication.room.Note
import com.deesha.notetakingapplication.viewmodel.MyViewModel

class UpdateNoteFragment : Fragment() {
    private var _binding : FragmentUpdateNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var myViewModel: MyViewModel
    private lateinit var currentNote:Note
    private val args:UpdateNoteFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myViewModel = (activity as MainActivity).viewModel
        currentNote = args.note!!

        binding.titleUpdateNote.setText(currentNote.title)
        binding.bodyUpdateNote.setText(currentNote.body)

        binding.floatingActionButtonUpdate.setOnClickListener{
            val title = binding.titleUpdateNote.text.toString().trim()
            val body = binding.bodyUpdateNote.text.toString().trim()
            if(title.isNotEmpty()){
                val note = Note(currentNote.id,title,body)
                myViewModel.updateNote(note)
                it.findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }else{
                Toast.makeText(context,"title can not be empty",Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun deleteNote(){
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Delete")
            setMessage("Do you want to delete this note?")
            setPositiveButton("Delete"){_,_->
                myViewModel.deleteNote(currentNote)

                view?.findNavController()?.navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }
            setNegativeButton("Cancel",null)
        }.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.update_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_update->{deleteNote()}
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}