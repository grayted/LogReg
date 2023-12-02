package com.example.veggieneighbors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.veggieneighbors.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogInActivity : AppCompatActivity() {

    lateinit var binding: ActivityLogInBinding
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = Firebase.auth

        binding.loginBtn.setOnClickListener {

            val email = binding.emailEdit.text.toString()
            val password = binding.passwordEdit.text.toString()

            login(email, password)
        }


        binding.signUpBtn.setOnClickListener {
            val intent: Intent = Intent(this@LogInActivity, SignUpActivity::class.java)

            startActivity(intent)
        }
    }
    private fun login(email: String, password: String) {

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent: Intent = Intent(this@LogInActivity, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this,"log suc",Toast.LENGTH_SHORT).show()
                    finish()

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"log fai",Toast.LENGTH_SHORT).show()
                    Log.d("login","err: ${task.exception}")
                }
            }

    }
}