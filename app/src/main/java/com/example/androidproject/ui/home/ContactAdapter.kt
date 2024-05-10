package com.example.androidproject.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.data.Contact

//private val contacts: List<Contact>,
class ContactAdapter( private val onClick: (Contact) -> Unit) : ListAdapter<Contact, ContactAdapter.ContactViewHolder>(ContactDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(view,onClick)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bind(contact)
    }

    class ContactViewHolder(itemView: View, val onClick: (Contact) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val firstNameTextView: TextView = itemView.findViewById(R.id.contact_initials)
        private val lastNameTextView: TextView = itemView.findViewById(R.id.contact_name)

        fun bind(contact: Contact) {
            val initials = "${contact.firstName.first()}${contact.lastName.first()}"
            firstNameTextView.text = initials
            val displayName = "${contact.firstName} ${contact.lastName}"
            lastNameTextView.text = displayName
            itemView.setOnClickListener { onClick(contact) }
        }
    }

    class ContactDiffCallback : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }
}