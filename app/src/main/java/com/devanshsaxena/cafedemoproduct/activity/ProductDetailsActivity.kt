package com.devanshsaxena.cafedemoproduct.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.devanshsaxena.cafedemoproduct.databinding.ActivityProductDetailsBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getProductDetails(intent.getStringExtra("id"))
    }

    private fun getProductDetails(proId: String?) {

        Firebase.firestore.collection("products")
            .document(proId!!).get().addOnSuccessListener {
                val list = it.get("productImages") as ArrayList<String>
                binding.name.text = it.getString("productName")
                binding.contactnumber.text = it.getString("productSp")
                binding.address.text = it.getString("productMrp")
                binding.servicename.text = it.getString("productDescription")
                Glide.with(this)
                    .load(it.getString("productCoverImg"))
                    .into(binding.personpicture)
            }
    }
}