package com.devanshsaxena.cafedemoproduct.fragament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.devanshsaxena.cafedemoproduct.R
import com.devanshsaxena.cafedemoproduct.adapter.CategoryAdapter
import com.devanshsaxena.cafedemoproduct.adapter.ProductAdapter
import com.devanshsaxena.cafedemoproduct.databinding.FragmentHomeBinding
import com.devanshsaxena.cafedemoproduct.model.AddProductModel
import com.devanshsaxena.cafedemoproduct.model.CategoryModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        val preference = requireContext().getSharedPreferences("info", AppCompatActivity.MODE_PRIVATE)


        if(preference.getBoolean("isCart",false))
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)


        getCategories()

        getSliderImage()

        getProducts()

        return binding.root
    }

    private fun getSliderImage() {
        Firebase.firestore.collection("slider").document("item")
            .get().addOnSuccessListener {
                Glide.with(requireContext()).load(it.get("img")).into(binding.sliderImage)
            }
    }

    private fun getProducts() {
        val list = ArrayList<AddProductModel>()
        Firebase.firestore.collection("products")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents){
                    val data = doc.toObject(AddProductModel::class.java)
                    list.add(data!!)
                }
                binding.productrecycler.adapter = ProductAdapter(requireContext(),list)
            }
    }

    private fun getCategories() {
        val list = ArrayList<CategoryModel>()
        Firebase.firestore.collection("categories")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents) {
                    val data = doc.toObject(CategoryModel::class.java)
                    list.add(data!!)
                }
                binding.categoryrecycler.adapter = CategoryAdapter(requireContext(), list)
                }
    }
}