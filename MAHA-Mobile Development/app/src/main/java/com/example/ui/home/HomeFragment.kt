package com.example.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mahaapp.R

class HomeFragment : Fragment() {

    private lateinit var tvGreeting: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        tvGreeting = view.findViewById(R.id.tv_greeting)

        // Ambil nama dari SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val fullName = sharedPreferences.getString("userName", "User")

        // Ambil kata pertama dari nama
        val firstName = fullName?.split(" ")?.firstOrNull() ?: "User"

        // Set teks greeting dengan resource string
        tvGreeting.text = getString(R.string.greeting_text, firstName)

        return view
    }
}
