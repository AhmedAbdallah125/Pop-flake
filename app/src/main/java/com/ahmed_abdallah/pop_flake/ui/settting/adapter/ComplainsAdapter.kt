package com.ahmed_abdallah.pop_flake.ui.settting.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmed_abdallah.pop_flake.databinding.ComplainLayoutBinding
import com.ahmed_abdallah.pop_flake.pojo.Complain

class ComplainsAdapter : RecyclerView.Adapter<ComplainsAdapter.ComplainsViewHolder>() {
    private var complainList: List<Complain> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setComplainList(complainList: List<Complain>) {
        this.complainList = complainList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ComplainsViewHolder(ComplainLayoutBinding.inflate(LayoutInflater.from(parent.context)))

    inner class ComplainsViewHolder(var binding: ComplainLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onBindViewHolder(holder: ComplainsViewHolder, position: Int) {
        val item = complainList[position]
        with(holder.binding) {
            txtName.text = item.name
            txtComplain.text = item.complainString
        }
    }

    override fun getItemCount() = complainList.size
}