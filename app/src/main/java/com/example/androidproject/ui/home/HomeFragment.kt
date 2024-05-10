package com.example.androidproject.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidproject.data.Contact
import com.example.androidproject.databinding.FragmentHomeBinding
import com.example.androidproject.ui.contactdetails.ContactDetailsActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.swipeToRefresh.setOnRefreshListener {
            homeViewModel.loadContactsFromJsonFile()
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ContactAdapter { contact ->
                navigateToContactDetail(contact)
            }
        }

        homeViewModel.contacts.observe(viewLifecycleOwner) {
            (binding.recyclerView.adapter as ContactAdapter).submitList(it)
        }

        return root
    }

    private fun navigateToContactDetail(contact: Contact) {
        val intent = Intent(context, ContactDetailsActivity::class.java).apply {
            putExtra("CONTACT_ID", contact.id)
            putExtra("CONTACT_FIRST_NAME", contact.firstName)
            putExtra("CONTACT_LAST_NAME", contact.lastName)
            putExtra("CONTACT_EMAIL", contact.email)
            putExtra("CONTACT_PHONE", contact.phone)
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}