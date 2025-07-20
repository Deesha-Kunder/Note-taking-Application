package com.deesha.notetakingapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.deesha.notetakingapplication.MainActivity
import com.deesha.notetakingapplication.R
import com.deesha.notetakingapplication.adpater.NoteAdapter
import com.deesha.notetakingapplication.databinding.FragmentHomeBinding
import com.deesha.notetakingapplication.room.Note
import com.deesha.notetakingapplication.viewmodel.MyViewModel


class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener {
private var _binding:FragmentHomeBinding?=null
    private val binding get() = _binding!!

    private lateinit var viewmodel:MyViewModel
    private lateinit var myAdapter:NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewmodel = (activity as MainActivity).viewModel
        setRecyclerView()
        binding.floatingActionButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_newNoteFragment)
        }
    }

    private fun setRecyclerView() {
        myAdapter = NoteAdapter()
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = myAdapter
        }
        activity?.let{
            viewmodel.getAllNote().observe(
                viewLifecycleOwner,{note -> myAdapter.differ.submitList(note)
                    updateUI(note)
                }

            )
        }
    }

    private fun updateUI(note: List<Note>?){
        if (note != null) {
            if(note.isNotEmpty()){
                binding.cardViewMain.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            }else{
                binding.cardViewMain.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.home_menu,menu)
        val search = menu.findItem(R.id.search_home).actionView as SearchView
        search.isSubmitButtonEnabled = true
        search.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        searchNote(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText!=null)
            searchNote(newText)
        return true
    }

    private fun searchNote(query: String) {
        var searchQuery = "$query"
        viewmodel.searchNote(searchQuery).observe(this,{list -> myAdapter.differ.submitList(list)})

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}