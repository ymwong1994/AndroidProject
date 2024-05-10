package com.example.androidproject.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ContactRepository(private val contactDao:ContactDao) {
    val allContacts: MutableLiveData<List<Contact>> = MutableLiveData()

    suspend fun insertAll(contacts: List<Contact>){
        contactDao.insertAll(contacts)
        allContacts.value = contactDao.getAllContacts()
    }

    suspend fun  getContact(id: String):Contact {
        return contactDao.getContact(id)
    }

    suspend fun update(contact: Contact){
        contactDao.updateContact(contact)
        allContacts.value = contactDao.getAllContacts()
    }

    suspend fun delete(contact: Contact){
        contactDao.delete(contact)
        allContacts.value = contactDao.getAllContacts()
    }

    suspend fun deleteAll(){
        contactDao.deleteAll()
        allContacts.value = contactDao.getAllContacts()
    }
}