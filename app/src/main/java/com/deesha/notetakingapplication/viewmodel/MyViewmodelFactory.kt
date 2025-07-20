package com.deesha.notetakingapplication.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.deesha.notetakingapplication.repository.MyRepository

class MyViewmodelFactory(val app:Application,private val repository: MyRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyViewModel(app ,repository) as T

    }
}