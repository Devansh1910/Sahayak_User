package com.devanshsaxena.cafedemoproduct.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.devanshsaxena.cafedemoproduct.databinding.ActivityRegisterBinding
import com.devanshsaxena.cafedemoproduct.model.UserModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            openLogin()
        }


        binding.signup.setOnClickListener {
            validateUser()
        }
    }

    private fun validateUser() {
        if(binding.userName.text!!.isEmpty() || binding.userPhoneNumber.text!!.isEmpty() || binding.userAddress.text!!.isEmpty())
            Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show()

        else
            storeData()
    }

    private fun storeData() {
        val builder = AlertDialog.Builder(this)
            .setTitle("Loading...")
            .setMessage("Please Wait")
            .setCancelable(false)
            .create()
        builder.show()

        val preferences = this.getSharedPreferences("user", MODE_PRIVATE)
        val editor = preferences.edit()

        editor.putString("number",binding.userPhoneNumber.text.toString())
        editor.putString("name",binding.userName.text.toString())
        editor.putString("address",binding.userAddress.text.toString())
        editor.apply()

        val data = UserModel(userName = binding.userName.text.toString(),userPhoneNumber = binding.userPhoneNumber.text.toString(), userAddress = binding.userAddress.text.toString() )


        Firebase.firestore.collection("users").document(binding.userPhoneNumber.text.toString())
            .set(data).addOnSuccessListener {
                Toast.makeText(this,"User Registered Successfully",Toast.LENGTH_SHORT).show()
                builder.dismiss()
                openLogin()

            }
            .addOnFailureListener{
                Toast.makeText(this,"Something went wrong..",Toast.LENGTH_SHORT).show()
                builder.dismiss()
            }
    }
    private fun openLogin() {
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }
}