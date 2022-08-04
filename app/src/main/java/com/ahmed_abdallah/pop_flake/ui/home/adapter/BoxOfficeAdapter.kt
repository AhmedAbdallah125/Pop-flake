package com.ahmed_abdallah.pop_flake.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmed_abdallah.pop_flake.databinding.TopBoxLayoutBinding
import com.ahmed_abdallah.pop_flake.pojo.BoxOfficeMovie

class BoxOfficeAdapter(
    private inline val action: (String) -> Unit
) : RecyclerView.Adapter<BoxOfficeAdapter.BoxViewHolder>() {
    private var topMoviesList: List<BoxOfficeMovie> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setTopMoviesList(topMoviesList: List<BoxOfficeMovie>) {
        this.topMoviesList = topMoviesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = BoxViewHolder(TopBoxLayoutBinding.inflate(LayoutInflater.from(parent.context)))

    inner class BoxViewHolder(var binding: TopBoxLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onBindViewHolder(holder: BoxViewHolder, position: Int) {
        val item = topMoviesList[position]
        with(holder.binding) {
            txtNum.text = item.rank
            txtProfit.text = item.weekend
            txtMovieTitle.text = item.title
        }
        holder.binding.root.setOnClickListener {
            action("${item.id}")
        }
    }

    override fun getItemCount() = topMoviesList.size
}