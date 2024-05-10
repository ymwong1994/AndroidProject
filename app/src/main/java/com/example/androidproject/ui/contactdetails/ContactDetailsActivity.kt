package com.example.androidproject.ui.contactdetails

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidproject.R
import com.example.androidproject.databinding.ActivityContactDetailsBinding
import com.example.androidproject.databinding.ActivityLoginBinding

class ContactDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Contact Details"

        val firstName = intent.getStringExtra("CONTACT_FIRST_NAME") ?: ""
        val lastName = intent.getStringExtra("CONTACT_LAST_NAME") ?: ""
        val email = intent.getStringExtra("CONTACT_EMAIL") ?: ""
        val phone = intent.getStringExtra("CONTACT_PHONE") ?: ""


        binding.firstNameEditText.setText(firstName)
        binding.lastNameEditText.setText(lastName)
        binding.emailEditText.setText(email)
        binding.phoneEditText.setText(phone)

        binding.initialsTextView.text = "${firstName.first()}${lastName.first()}"

        binding.updateButton.setOnClickListener {

        }

        binding.removeButton.setOnClickListener {

        }
    }
}