package com.example.androidproject.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidproject.data.AppDatabase
import com.example.androidproject.data.Contact
import com.example.androidproject.data.ContactRepository
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) :AndroidViewModel(application) {
    val contact: LiveData<Contact>
    private val repository: ContactRepository

    init {
        val contactDao = AppDatabase.getDatabase(application, viewModelScope).contactDao()
        repository = ContactRepository(contactDao)
        contact = queryContact("from sharedPreference")
    }

    fun queryContact(id: String)= viewModelScope.launch {
        repository.getContact(id)
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }
}