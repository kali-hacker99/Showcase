package com.first.showcasetrial

import android.app.ProgressDialog
import android.app.ProgressDialog.show
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.first.showcasetrial.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {


private lateinit var signup_fullname:EditText
private lateinit var signup_username:EditText
private lateinit var signup_email:EditText
private lateinit var signup_password:EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)



        val sign_in = findViewById<Button>(R.id.signin_link_btn)
        sign_in.setOnClickListener {
          //  startActivity(Intent(this,SignInActivity::class.java))
        onBackPressed()
        }
        var signup = findViewById<Button>(R.id.signup_btn)
       signup.setOnClickListener {
           signup_fullname = findViewById(R.id.fullname_signup)
           signup_username = findViewById(R.id.username_signup)
           signup_email = findViewById(R.id.email_signup)
           signup_password= findViewById(R.id.password_signup)
            CreateAccount()
        }

    }

    private fun CreateAccount() {


        val fullname  = signup_fullname.text.toString()
        val username  = signup_username.text.toString()
        val email  = signup_email.text.toString()
        val password  = signup_password.text.toString()

        when {
            TextUtils.isEmpty(fullname) ->
                Toast.makeText(this, "Full Name is required", Toast.LENGTH_LONG).show()
            TextUtils.isEmpty(username) ->
                Toast.makeText(this, "Username is required", Toast.LENGTH_LONG).show()
            TextUtils.isEmpty(email) ->
                Toast.makeText(this, "Email is required", Toast.LENGTH_LONG).show()
            TextUtils.isEmpty(password) ->
                Toast.makeText(this, "Password is required", Toast.LENGTH_LONG).show()


            else -> {
                 val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("SignUP")
                progressDialog.setMessage("Please wait...")
                progressDialog.setCanceledOnTouchOutside(false)
                progressDialog.show()
                val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
                mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener (
                        {
                                task ->
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
                    )

            }
        }




    }

}