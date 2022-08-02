package com.ahmed_abdallah.pop_flake.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmed_abdallah.pop_flake.databinding.MovieLayoutBinding
import com.ahmed_abdallah.pop_flake.pojo.Movie
import com.bumptech.glide.Glide

class ComingAdapter : RecyclerView.Adapter<ComingAdapter.ComingViewHolder>() {

    private var movies: List<Movie> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setMoviesComingSoon(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }


    inner class ComingViewHolder(private val binding: MovieLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val movie get() = movies[bindingAdapterPosition]

        fun bindData() {
            Glide.with(binding.root)
                .load(movie.image)
                .into(binding.imgMovie)
            with(binding) {
                txtMovieName.text = movie.title ?: "None"
                handleRatingViews(this)
                dateMovie.text = movie.year
                movie.year?.let{
                    dateMovie.text = it
                }
            }
        }

        private fun handleRatingViews(binding: MovieLayoutBinding) {
//            movie.imDbRating?.let {
//                changeVisibility(true, binding)
//                binding.txtRating.text = it
//            } ?: run {
//                changeVisibility(false, binding)
//            }
            changeVisibility(true,binding)
        }

        private fun changeVisibility(isRatingNull: Boolean, binding: MovieLayoutBinding) {
            with(binding) {
                if (isRatingNull) {
                    imageView.visibility = INVISIBLE
                    txtRating.visibility = INVISIBLE
                } else {
                    imageView.visibility = VISIBLE
                    txtRating.visibility = VISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) =
        ComingViewHolder(
            MovieLayoutBinding.inflate(LayoutInflater.from(parent.context))
        )


    override fun onBindViewHolder(holder: ComingViewHolder, position: Int) = holder.bindData()


    override fun getItemCount() = movies.size
}