package com.example.projectandroidmas

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.projectandroidmas.databinding.ActivityLupaPasswordBinding

class LupaPassword : AppCompatActivity() {

    private lateinit var binding: ActivityLupaPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLupaPasswordBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setContentView(R.layout.activity_lupa_password)




        // Kembali ke login
        binding.btnKembaliKeLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}