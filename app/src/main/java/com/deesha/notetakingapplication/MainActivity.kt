package com.deesha.notetakingapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.deesha.notetakingapplication.databinding.ActivityMainBinding
import com.deesha.notetakingapplication.repository.MyRepository
import com.deesha.notetakingapplication.room.NoteDatabase
import com.deesha.notetakingapplication.viewmodel.MyViewModel
import com.deesha.notetakingapplication.viewmodel.MyViewmodelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    lateinit var viewModel:MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewModel()
    }

    private fun setUpViewModel() {
        val repository = MyRepository(NoteDatabase(this))
        val factory = MyViewmodelFactory(application,repository)
        viewModel = ViewModelProvider(this, factory ).get(MyViewModel::class.java)
    }
}