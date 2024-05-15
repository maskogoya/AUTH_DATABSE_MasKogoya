package com.example.projectandroidmas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectandroidmas.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var  klubAdapter: KlubAdapter
    private lateinit var user: FirebaseAuth
    private lateinit var dataRecyclerView: RecyclerView

    private lateinit var listKlub: MutableList<DataClub>
    private  var  mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = FirebaseAuth.getInstance()

        dataRecyclerView = findViewById(R.id.listData)

        dataRecyclerView.setHasFixedSize(true)
        dataRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.myDataLoaderprogressBar.visibility = View.VISIBLE

        listKlub = ArrayList()
        klubAdapter = KlubAdapter(this@MainActivity, listKlub)
        dataRecyclerView.adapter = klubAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("klub")
        mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()
                binding.myDataLoaderprogressBar.visibility = View.VISIBLE
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                listKlub.clear()
                for (teacherSnapshot in snapshot.children) {
                    val upload = teacherSnapshot.getValue(DataClub::class.java)
                    upload!!.key = teacherSnapshot.key
                    listKlub.add(upload)
                }
                klubAdapter.notifyDataSetChanged()
                binding.myDataLoaderprogressBar.visibility = View.GONE
            }

        })

        binding.out.setOnClickListener {
            user.signOut()

            Intent(this,  Login::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }

        }


    }
    }

