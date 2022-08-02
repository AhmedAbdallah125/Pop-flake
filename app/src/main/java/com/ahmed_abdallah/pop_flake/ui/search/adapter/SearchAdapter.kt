package com.ahmed_abdallah.pop_flake.ui.search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmed_abdallah.pop_flake.databinding.SearchMovieBinding
import com.ahmed_abdallah.pop_flake.pojo.SearchResult
import com.bumptech.glide.Glide

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private var searchResultList: List<SearchResult> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setSearchMovieList(searchResultList: List<SearchResult>) {
        this.searchResultList = searchResultList
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
        val item = searchResultList[position]
        with(holder.binding) {
            movieDescription.text = item.description
            movieTitle.text = item.title
            Glide.with(root).load(item.image).into(movieImg)
        }

    }

    override fun getItemCount() = searchResultList.size
}