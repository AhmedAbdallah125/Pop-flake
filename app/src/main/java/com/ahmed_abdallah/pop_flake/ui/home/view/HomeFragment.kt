package com.ahmed_abdallah.pop_flake.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ahmed_abdallah.pop_flake.Utils.ResultState
import com.ahmed_abdallah.pop_flake.databinding.FragmentHomeBinding
import com.ahmed_abdallah.pop_flake.ui.home.adapter.*
import com.ahmed_abdallah.pop_flake.ui.home.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.withContext

@AndroidEntryPoint
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

    private var _posterAdapter: PosterAdapter? = null
    private val posterAdapter get() = _posterAdapter!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecycles()
//        view()
    }

    private fun view() {

        viewModel.getInComingMovies()
        viewModel.getInBoxOfficeMovies()
        viewModel.getInTheatreMovies()
        viewModel.getTopRatedMovies()
        viewModel.getHeaderShows()
        handleProgress()
        handleInTheatresMovies()
        handleComingMovies()
        handleTopRatedMovies()
        handleBoxOfficesMovies()
        handleViewPager()

    }

    private fun handleViewPager() {
            lifecycleScope.launchWhenStarted {
                viewModel.headerShows.collect { result ->
                    when (result) {
                        ResultState.EmptyResult -> {
                            Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT)

                            posterAdapter.setMoviesComingSoon(emptyList())

                        }
                        is ResultState.Error -> {
                            Toast.makeText(
                                requireContext(),
                                "Error${result.errorString}",
                                Toast.LENGTH_SHORT
                            )
                        }
                        ResultState.Loading -> {
                            Toast.makeText(requireContext(), "Looo", Toast.LENGTH_SHORT)
                        }
                        is ResultState.Success -> {
                            posterAdapter.setMoviesComingSoon(result.data)

                        }
                    }
                }
            }
        }


    private fun handleBoxOfficesMovies() {
        lifecycleScope.launchWhenStarted {
            viewModel.boxOfficeMovies.collect { result ->
                when (result) {
                    ResultState.EmptyResult -> {
                        Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT)

                        boxOfficeAdapter.setTopMoviesList(emptyList())

                    }
                    is ResultState.Error -> {
                        Toast.makeText(
                            requireContext(),
                            "Error${result.errorString}",
                            Toast.LENGTH_SHORT
                        )
                    }
                    ResultState.Loading -> {
                        Toast.makeText(requireContext(), "Looo", Toast.LENGTH_SHORT)
                    }
                    is ResultState.Success -> {
                        boxOfficeAdapter.setTopMoviesList(result.data)
                    }
                }
            }
        }
    }

    private fun handleTopRatedMovies() {
        lifecycleScope.launchWhenStarted {
            viewModel.topRatedMovies.collect { result ->
                when (result) {
                    ResultState.EmptyResult -> {
                        Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT)

                        topRatedAdapter.setMoviesTopRated(emptyList())

                    }
                    is ResultState.Error -> {
                        Toast.makeText(
                            requireContext(),
                            "Error${result.errorString}",
                            Toast.LENGTH_SHORT
                        )
                    }
                    ResultState.Loading -> {
                        Toast.makeText(requireContext(), "Looo", Toast.LENGTH_SHORT)
                    }
                    is ResultState.Success -> {
                        topRatedAdapter.setMoviesTopRated(result.data)
                    }
                }
            }
        }
    }

    private fun handleComingMovies() {
        lifecycleScope.launchWhenStarted {
            viewModel.comingMovies.collect { result ->
                when (result) {
                    ResultState.EmptyResult -> {
                        Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT)

                        comingAdapter.setMoviesComingSoon(emptyList())

                    }
                    is ResultState.Error -> {
                        Toast.makeText(
                            requireContext(),
                            "Error${result.errorString}",
                            Toast.LENGTH_SHORT
                        )
                    }
                    ResultState.Loading -> {
                        Toast.makeText(requireContext(), "Looo", Toast.LENGTH_SHORT)
                    }
                    is ResultState.Success -> {
                        comingAdapter.setMoviesComingSoon(result.data)
                    }
                }
            }
        }
    }

    private fun handleInTheatresMovies() {
        lifecycleScope.launchWhenStarted {
            viewModel.inTheatresMovies.buffer().collect { result ->
                when (result) {
                    ResultState.EmptyResult -> {
                        Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT)

                        inTheatreAdapter.setMoviesInTheatre(emptyList())

                    }
                    is ResultState.Error -> {
                        Toast.makeText(
                            requireContext(),
                            "Error${result.errorString}",
                            Toast.LENGTH_SHORT
                        )
                    }
                    ResultState.Loading -> {
                        Toast.makeText(requireContext(), "Looo", Toast.LENGTH_SHORT)

                        inTheatreAdapter.setMoviesInTheatre(emptyList())
                    }
                    is ResultState.Success -> {
                        inTheatreAdapter.setMoviesInTheatre(result.data)
                    }
                }
            }
        }
    }

    private fun handleProgress() {
        lifecycleScope.launchWhenStarted {
            viewModel.progressVisibility.collect { visible ->
                handleViewsVisibility(visible)
            }
        }
    }

    private fun handleViewsVisibility(visible: Boolean) {
        val progressVisibility = if (visible) VISIBLE else GONE
        val otherViewVisibility = if (visible) INVISIBLE else VISIBLE
        with(binding) {
            progressCircular.visibility = progressVisibility
            recyclerViewTopBoxOffice.visibility = otherViewVisibility
            recyclerViewTopRating.visibility = otherViewVisibility
            recyclerViewInTheatre.visibility = otherViewVisibility
            recyclerViewComingSoon.visibility = otherViewVisibility
            viewPager.visibility = otherViewVisibility

        }
    }

    private fun initRecycles() {
        initComingRecycler()
        initInTheatreRecycler()
        initTopRatedRecycler()
        initTopMoviesRecycler()
        initViewPager()
    }

    private fun initViewPager() {
        _posterAdapter = PosterAdapter()
        binding.viewPager.adapter = posterAdapter
//        binding.viewPager.autoScroll(3000)
    }

    fun ViewPager2.autoScroll(interval: Long) {
        var index = 0
        val count = adapter?.itemCount ?: 0
        lifecycleScope.launchWhenStarted {
            while (true) {
                withContext(Dispatchers.IO) {
                    delay(interval)
                    withContext(Dispatchers.Main) {
                      setCurrentItem(index++%count,true)
                    }
                }
            }

        }
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                index = position + 1
            }

        })
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
    }


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

    private fun initTopMoviesRecycler() {
        _boxOfficeAdapter = BoxOfficeAdapter()
        binding.recyclerViewTopBoxOffice.apply {
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
        _comingAdapter = null
        _boxOfficeAdapter = null
        _inTheatreAdapter = null
        _topRatedAdapter = null
    }
}