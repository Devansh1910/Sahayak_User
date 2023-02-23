package com.devanshsaxena.cafedemoproduct.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devanshsaxena.cafedemoproduct.databinding.FragmentMoreBinding
import com.devanshsaxena.cafedemoproduct.model.UserModel

class UserAdapter (val context: Context, val list: ArrayList<UserModel>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    inner class UserViewHolder(val binding : FragmentMoreBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = FragmentMoreBinding.inflate(LayoutInflater.from(context),parent,false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val data = list[position]

        Glide.with(context).load(data.userCoverImg).into(holder.binding.userimg)
        holder.binding.username.text = data.userName
        holder.binding.userphonenumber.text = data.userPhoneNumber
        holder.binding.useraddress.text = data.userAddress

    }

    override fun getItemCount(): Int {
        return list.size
    }
}