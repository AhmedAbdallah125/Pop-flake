package com.ahmed_abdallah.pop_flake.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmed_abdallah.pop_flake.databinding.MovieLayoutBinding
import com.ahmed_abdallah.pop_flake.pojo.Movie
import com.bumptech.glide.Glide

class InTheatreAdapter : RecyclerView.Adapter<InTheatreAdapter.InTheatreViewHolder>() {

    private var movies: List<Movie> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setMoviesInTheatre(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }


    inner class InTheatreViewHolder(private val binding: MovieLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val movie get() = movies[bindingAdapterPosition]

        fun bindData() {
            Glide.with(binding.root)
                .load(movie.image)
                .into(binding.imgMovie)
            with(binding) {
                txtMovieName.text = movie.title ?: "None"
                handleRatingViews(this)
            }
        }

        private fun handleRatingViews(binding: MovieLayoutBinding) {
            movie.imDbRating?.let {
                changeVisibility(true, binding)
                binding.txtRating.text = it
            } ?: run {
                changeVisibility(false, binding)
            }
        }

        private fun changeVisibility(isRatingNull: Boolean, binding: MovieLayoutBinding) {
            with(binding) {
                if (isRatingNull) {
                    imageView.visibility = View.INVISIBLE
                    txtRating.visibility = View.INVISIBLE
                } else {
                    imageView.visibility = View.VISIBLE
                    txtRating.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) =
        InTheatreViewHolder(
            MovieLayoutBinding.inflate(LayoutInflater.from(parent.context))
        )


    override fun onBindViewHolder(holder: InTheatreViewHolder, position: Int) = holder.bindData()


    override fun getItemCount() = movies.size
}