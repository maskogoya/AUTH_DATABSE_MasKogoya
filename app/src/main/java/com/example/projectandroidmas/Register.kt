package com.example.projectandroidmas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.projectandroidmas.databinding.ActivityLoginBinding
import com.example.projectandroidmas.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
            val email : String = binding.edtEmail.text.toString().trim()
            val password : String = binding.edtPassword.text.toString().trim()
            val  confirmPassword : String = binding.edtKonfirmasi.text.toString().trim()

            if (email.isEmpty()){
                binding.edtEmail.error = "Input Email"
                binding.edtEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtEmail.error ="Iil"
                binding.edtEmail.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty() || password.length < 6){
                binding.edtPassword.error = "password be more tthan 6 characters"
                binding.edtPassword.requestFocus()
                return@setOnClickListener            }

            if(password != confirmPassword){
                binding.edtKonfirmasi.error = "password mus be match"
                binding.edtKonfirmasi.requestFocus()
                return@setOnClickListener
            }
            registerUser(email, password)
        }



        // Tombol ke lupa Login
//        binding.btnRegister.setOnClickListener{
//            val intent = Intent(this, Login::class.java)
//            startActivity (intent)
//        }

        // Link ke lupa Login
        binding.tvKeLogin.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity (intent)
        }
    }

    private fun registerUser(email: String, password: String) {


        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                Intent(this, Login::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
            else{
                Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }




    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            Intent(this, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }

}