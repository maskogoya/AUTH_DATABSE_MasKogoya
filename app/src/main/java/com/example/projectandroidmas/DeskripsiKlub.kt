package com.example.projectandroidmas

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projectandroidmas.databinding.ActivityDeskripsiKlubBinding
import com.example.projectandroidmas.databinding.ActivityMainBinding

class DeskripsiKlub : AppCompatActivity() {

    private lateinit var binding : ActivityDeskripsiKlubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_deskripsi_klub)
        binding = ActivityDeskripsiKlubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intess = intent
        var deskT = intess.getStringExtra("DESKRIPSI")
        var jdlT = intess.getStringExtra("KLUB")
        var imgT = intess.getStringExtra("GAMBAR")

        binding.imgKlub.loadImage(imgT)
        binding.nmKlub.text =jdlT
        binding.desk.text =deskT
    }
    }
