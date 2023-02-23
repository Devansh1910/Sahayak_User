package com.devanshsaxena.cafedemoproduct.fragament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.devanshsaxena.cafedemoproduct.R
import com.devanshsaxena.cafedemoproduct.databinding.FragmentMoreBinding

@Suppress("UNREACHABLE_CODE")
class MoreFragment : Fragment() {

    private lateinit var binding: FragmentMoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val preference =
            requireContext().getSharedPreferences("info", AppCompatActivity.MODE_PRIVATE)

        if (preference.getBoolean("isCart", false))
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)

        return binding.root

    }
}

