package com.first.showcasetrial

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var signin_email: EditText
    private lateinit var signin_password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signin_email = findViewById(R.id.email_login)
        signin_password= findViewById(R.id.password_login)

        val signup = findViewById<Button>(R.id.signup_link_btn)
        signup.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }
        val login = findViewById<Button>(R.id.login_btn)
        login.setOnClickListener {
            loginUser()
        }


    }

    private fun loginUser() {
        val email = signin_email.text.toString().trim()
        val password = signin_password.text.toString().trim()

        when{
            TextUtils.isEmpty(email)-> Toast.makeText(this,"Email is required",Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(password)-> Toast.makeText(this,"Password is required",Toast.LENGTH_SHORT).show()

            else -> {
                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("SignUP")
                progressDialog.setMessage("Please wait...")
                progressDialog.setCanceledOnTouchOutside(false)
                progressDialog.show()

                val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        progressDialog.dismiss()
                        val intent = Intent(this,MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        val message = task.exception!!.toString()
                        Toast.makeText(this,"Error: $message",Toast.LENGTH_LONG).show()
                        mAuth.signOut()
                        progressDialog.dismiss()

                    }
                }

            }

        }



    }


    override fun onStart() {
        super.onStart()
        if(FirebaseAuth.getInstance().currentUser !=null){
            val intent = Intent(this@SignInActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }

}

