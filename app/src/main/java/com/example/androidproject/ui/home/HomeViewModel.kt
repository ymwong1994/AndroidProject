package com.example.androidproject.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidproject.data.AppDatabase
import com.example.androidproject.data.Contact
import com.example.androidproject.data.ContactRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val contacts: LiveData<List<Contact>>

    private val repository: ContactRepository

    init {
        val contactDao = AppDatabase.getDatabase(application, viewModelScope).contactDao()
        repository = ContactRepository(contactDao)
        contacts = repository.allContacts
    }

    fun insertAll(contacts:List<Contact>) = viewModelScope.launch {
        repository.insertAll(contacts)
    }

    fun update(contact:Contact) = viewModelScope.launch {
        repository.update(contact)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun loadContactsFromJsonFile() {
        val jsonString = getJsonDataFromAsset("data.json")
        jsonString?.let {
            val gson = Gson()
            val contactType = object : TypeToken<List<Contact>>() {}.type
            val contactList: List<Contact> = gson.fromJson(it, contactType)
            deleteAll()
            insertAll(contactList)
        }
    }

    private fun getJsonDataFromAsset(fileName: String): String? {
        return try {
            val inputStream = getApplication<Application>().assets.open(fileName)
            inputStream.bufferedReader().use { it.readText() }
        } catch (ex: IOException) {
            Log.e("HomeViewModel", "Error reading from JSON file", ex)
            null
        }
    }
}