package com.example.mahaapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.ui.login.LoginActivity

class ProfilFragment : Fragment() {

    // Parameter untuk fragment (opsional, bisa dihapus jika tidak diperlukan)
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout fragment
        val rootView = inflater.inflate(R.layout.fragment_profil, container, false)

        // Temukan ImageView logout berdasarkan ID
        val logoutImageView: ImageView = rootView.findViewById(R.id.logoutImageView)

        // Set OnClickListener untuk menangani aksi klik
        logoutImageView.setOnClickListener {
            // Proses logout dengan menghapus data login dari SharedPreferences
            val sharedPreferences = activity?.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences?.edit()
            editor?.clear() // Menghapus data login
            editor?.apply()

            // Arahkan ke LoginActivity dan clear stack activity
            val intent = Intent(activity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)

            // Tutup fragment ini agar tidak ada yang tertinggal di stack
            activity?.finish()
        }

        return rootView
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        // Factory method untuk membuat instance baru dari fragment ini
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
