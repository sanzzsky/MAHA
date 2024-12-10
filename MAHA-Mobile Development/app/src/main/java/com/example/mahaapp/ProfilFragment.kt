package com.example.mahaapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ui.login.LoginActivity

class ProfilFragment : Fragment() {

    private lateinit var tvName: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout fragment
        val rootView = inflater.inflate(R.layout.fragment_profil, container, false)

        // Temukan TextView untuk nama pengguna
        tvName = rootView.findViewById(R.id.tvName)

        // Pastikan activity dan sharedPreferences tidak null
        val sharedPreferences = activity?.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        sharedPreferences?.let {
            // Ambil nama lengkap dari SharedPreferences, dengan default "User"
            val userName = it.getString("userName", "User")

            // Setel nama lengkap pada TextView
            tvName.text = userName
        }

        // Temukan ImageView logout dan set onClickListener
        val logoutImageView: ImageView = rootView.findViewById(R.id.logoutImageView)
        logoutImageView.setOnClickListener {
            // Proses logout dengan menghapus data login dari SharedPreferences
            sharedPreferences?.let { prefs ->
                val editor = prefs.edit()
                editor.clear() // Menghapus data login
                editor.apply()

                // Arahkan ke LoginActivity dan clear stack activity
                val intent = Intent(activity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)

                // Tutup fragment ini agar tidak ada yang tertinggal di stack
                activity?.finish()
            }
        }

        return rootView
    }
}
