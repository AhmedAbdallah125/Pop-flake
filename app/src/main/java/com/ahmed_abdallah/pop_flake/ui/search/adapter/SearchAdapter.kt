package com.ahmed_abdallah.pop_flake.ui.search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmed_abdallah.pop_flake.databinding.SearchMovieBinding
import com.ahmed_abdallah.pop_flake.pojo.TopRatedMovie
import com.bumptech.glide.Glide

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private var searchMovieList: List<TopRatedMovie> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setSearchMovieList(searchMovieList: List<TopRatedMovie>) {
        this.searchMovieList = searchMovieList
        notifyDataSetChanged()
    }

    inner class SearchViewHolder(val binding: SearchMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchViewHolder =
        SearchViewHolder(SearchMovieBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = searchMovieList[position]
        with(holder.binding) {
            movieActors.text = item.crew
            movieYear.text = item.year
            movieTitle.text = item.title
            Glide.with(root).load(item.image).into(movieImg)
        }

    }

    override fun getItemCount() = searchMovieList.size
}