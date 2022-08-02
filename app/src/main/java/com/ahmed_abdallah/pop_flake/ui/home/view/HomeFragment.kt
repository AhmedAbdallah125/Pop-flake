package com.ahmed_abdallah.pop_flake.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmed_abdallah.pop_flake.databinding.FragmentHomeBinding
import com.ahmed_abdallah.pop_flake.pojo.Movie
import com.ahmed_abdallah.pop_flake.ui.home.adapter.BoxOfficeAdapter
import com.ahmed_abdallah.pop_flake.ui.home.adapter.ComingAdapter
import com.ahmed_abdallah.pop_flake.ui.home.adapter.InTheatreAdapter
import com.ahmed_abdallah.pop_flake.ui.home.adapter.TopRatedAdapter
import com.ahmed_abdallah.pop_flake.ui.home.viewModel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var _comingAdapter: ComingAdapter? = null
    private val comingAdapter get() = _comingAdapter!!

    private var _inTheatreAdapter: InTheatreAdapter? = null
    private val inTheatreAdapter get() = _inTheatreAdapter!!

    private var _topRatedAdapter: TopRatedAdapter? = null
    private val topRatedAdapter get() = _topRatedAdapter!!

    private var _boxOfficeAdapter: BoxOfficeAdapter? = null
    private val boxOfficeAdapter get() = _boxOfficeAdapter!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecycles()
    }

    private fun initRecycles() {
        initComingRecycler()
        initInTheatreRecycler()
        initTopRatedRecycler()
        initTopMoviesRecycler()
    }

    private fun initComingRecycler() {
        _comingAdapter = ComingAdapter()
        binding.recyclerViewComingSoon.apply {
            adapter = comingAdapter
            layoutManager =
                LinearLayoutManager(context).apply {
                    orientation = RecyclerView.HORIZONTAL
                }
            setHasFixedSize(true)
        }

    }

    private fun initInTheatreRecycler() {
        _inTheatreAdapter = InTheatreAdapter()
        binding.recyclerViewInTheatre.apply {
            adapter = inTheatreAdapter
            layoutManager =
                LinearLayoutManager(context).apply {
                    orientation = RecyclerView.HORIZONTAL
                }
            setHasFixedSize(true)
        }
        inTheatreAdapter.setMoviesInTheatre(
            arrayListOf(
                Movie(
                    title = "Ahmed",
                    image = "https://m.media-amazon.com/images/M/MV5BMGIyNTI3NWItNTJkOS00MGYyLWE4NjgtZDhjMWQ4Y2JkZTU5XkEyXkFqcGdeQXVyNjY1MTg4Mzc@._V1_UX128_CR0,3,128,176_AL_.jpg"
                ),
                Movie(
                    title = "Mohamed",
                    image = "https://m.media-amazon.com/images/M/MV5BMGIyNTI3NWItNTJkOS00MGYyLWE4NjgtZDhjMWQ4Y2JkZTU5XkEyXkFqcGdeQXVyNjY1MTg4Mzc@._V1_UX128_CR0,3,128,176_AL_.jpg"
                ),
                Movie(
                    title = "Ahmed",
                    image = "https://m.media-amazon.com/images/M/MV5BMGIyNTI3NWItNTJkOS00MGYyLWE4NjgtZDhjMWQ4Y2JkZTU5XkEyXkFqcGdeQXVyNjY1MTg4Mzc@._V1_UX128_CR0,3,128,176_AL_.jpg"
                ),
                Movie(
                    title = "Ahmed",
                    image = "https://m.media-amazon.com/images/M/MV5BMGIyNTI3NWItNTJkOS00MGYyLWE4NjgtZDhjMWQ4Y2JkZTU5XkEyXkFqcGdeQXVyNjY1MTg4Mzc@._V1_UX128_CR0,3,128,176_AL_.jpg"
                ),
                Movie(
                    title = "Ahmed",
                    image = "https://m.media-amazon.com/images/M/MV5BMGIyNTI3NWItNTJkOS00MGYyLWE4NjgtZDhjMWQ4Y2JkZTU5XkEyXkFqcGdeQXVyNjY1MTg4Mzc@._V1_UX128_CR0,3,128,176_AL_.jpg"
                ),

                )
        )
    }
//
    private fun initTopRatedRecycler() {
        _topRatedAdapter = TopRatedAdapter()
        binding.recyclerViewTopRating.apply {
            adapter = topRatedAdapter
            layoutManager =
                LinearLayoutManager(context).apply {
                    orientation = RecyclerView.HORIZONTAL
                }
            setHasFixedSize(true)
        }
    }
//
    private fun initTopMoviesRecycler() {
        _boxOfficeAdapter = BoxOfficeAdapter()
        binding.recyclerViewTopRating.apply {
            adapter = boxOfficeAdapter
            layoutManager =
                LinearLayoutManager(context).apply {
                    orientation = RecyclerView.VERTICAL
                }
            setHasFixedSize(true)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}