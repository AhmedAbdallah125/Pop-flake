package com.ahmed_abdallah.pop_flake.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmed_abdallah.pop_flake.databinding.TrailerLayoutBinding
import com.ahmed_abdallah.pop_flake.pojo.PosterAPI
import com.ahmed_abdallah.pop_flake.pojo.Trailer
import com.bumptech.glide.Glide

class PosterAdapter(
    private inline val trailerAction: (String) -> Unit,
    private inline val posterAction: (String) -> Unit


) : RecyclerView.Adapter<PosterAdapter.PosterViewHolder>() {

    private var headerList: List<Pair<PosterAPI, Trailer>> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setMoviesComingSoon(movies: List<Pair<PosterAPI, Trailer>>) {
        this.headerList = movies
        notifyDataSetChanged()
    }


    inner class PosterViewHolder(private val binding: TrailerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val header get() = headerList[bindingAdapterPosition]

        init {
            binding.imgTrailer.setOnClickListener {
                trailerAction(header.second.link.toString())
            }
            binding.imgPoster.setOnClickListener {
                trailerAction("${header.first.imDbId}")
            }
        }

        fun bindData() {
            Glide.with(binding.root)
                .load(header.second.thumbnailUrl)
                .into(binding.imgTrailer)
            binding.txtMovieTiltle.text = header.second.fullTitle
            Glide.with(binding.root)
                .load(header.first.posters[0].link)
                .into(binding.imgPoster)
        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) =
        PosterViewHolder(
            TrailerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) = holder.bindData()


    override fun getItemCount() = headerList.size
}