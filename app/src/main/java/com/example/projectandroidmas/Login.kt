package com.example.projectandroidmas

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.wifi.hotspot2.pps.HomeSp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.projectandroidmas.databinding.ActivityLoginBinding
import com.example.projectandroidmas.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)



        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginBtn.setOnClickListener {

            val email: String = binding.edtEmailAddress.text.toString().trim()
            val password: String = binding.edtPassword.text.toString().trim()

            if (email.isEmpty()) {
                binding.edtEmailAddress.error = "Input Email"
                binding.edtEmailAddress.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edtEmailAddress.error = "Invalid Email"
                binding.edtEmailAddress.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6) {
                binding.edtPassword.error = "password be more tthan 6 characters"
                binding.edtPassword.requestFocus()
                return@setOnClickListener
            }


            loginUser(email, password)


        }

        binding.lupaBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.dialogforgot, null)
            val userEmail = view.findViewById<EditText>(R.id.editBox)
            builder.setView(view)
            val dialog = builder.create()
            view.findViewById<Button>(R.id.btnReset).setOnClickListener {
//                compareEmail(userEmail)
//                dialog.dismiss()
                compareEmail(userEmail)
            }
            view.findViewById<Button>(R.id.btnCancel).setOnClickListener {
                dialog.dismiss()
            }
            if (dialog.window != null){
                dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            }
            dialog.show()
        }




        // Link ke Register
        binding.tvLinkToRegister.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        // Link ke lupa password
//        binding.linkLupaPassword.setOnClickListener{
//            val intent = Intent(this, LupaPassword::class.java)
//            startActivity(intent)
//        }

//        // Tombol ke MainActivity / Home
//        binding.loginBtn.setOnClickListener{
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
    }

    private fun compareEmail(userEmail: EditText) {
        if (userEmail.text.toString().isEmpty()){
            return
        }



        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail.text.toString()).matches()){
            return
        }

        firebaseAuth.sendPasswordResetEmail(userEmail.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Check your email", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun loginUser(email: String, password: String) {

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Intent(this, MainActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            } else {
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