package com.example.projectandroidmas

import com.google.firebase.firestore.Exclude

data class DataClub(


    val deskripsi : String? =null,
    val gambar : String? = null,
    val nama : String? = null,


    @get:Exclude
    @set:Exclude
    var key:String? = null

    )
