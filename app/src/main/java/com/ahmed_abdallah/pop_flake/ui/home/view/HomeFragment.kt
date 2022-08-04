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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ahmed_abdallah.pop_flake.Utils.ResultState
import com.ahmed_abdallah.pop_flake.Utils.isConnected
import com.ahmed_abdallah.pop_flake.Utils.showSnack
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
        view()
        handleRefresher()
        handleSnackBar()
    }

    private fun initRecycles() {
        initComingRecycler()
        initInTheatreRecycler()
        initTopRatedRecycler()
        initTopMoviesRecycler()
        initViewPager()
    }
    private fun view() {
        if (isConnected(requireContext())) {
            requestData()
            handleProgress()
            handleInTheatresMovies()
            handleComingMovies()
            handleTopRatedMovies()
            handleBoxOfficesMovies()
            handleViewPager()
            viewModel.resetSnack()
        } else
            viewModel.finishLoading()
    }

    private fun requestData() {
        viewModel.getInComingMovies()
        viewModel.getInBoxOfficeMovies()
        viewModel.getInTheatreMovies()
        viewModel.getTopRatedMovies()
        viewModel.getHeaderShows()
    }


    private fun handleSnackBar() {
        lifecycleScope.launchWhenStarted {
            viewModel.showSnackBar.collect {
                if (it)
                    showSnack(message = "Bad Connection")
            }
        }
    }


    private fun handleRefresher() {
        binding.refreshing.setOnRefreshListener {
            if (isConnected(requireContext())) {
                viewModel.refreshData()
                viewModel.resetSnack()
            } else
                viewModel.finishLoading()

            binding.refreshing.isRefreshing = false

        }
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
                        binding.viewPager.autoScroll(0, false)
                    }
                    is ResultState.Success -> {
                        posterAdapter.setMoviesComingSoon(result.data)
                        binding.viewPager.autoScroll(4000, true)

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


    private fun initViewPager() {
        _posterAdapter = PosterAdapter(openTrailer, openPoster)
        binding.viewPager.adapter = posterAdapter
    }

    fun ViewPager2.autoScroll(interval: Long, loop: Boolean) {
        var index = 0
        val count = adapter?.itemCount ?: 0
        lifecycleScope.launchWhenStarted {
            while (loop) {
                withContext(Dispatchers.IO) {
                    delay(interval)
                    withContext(Dispatchers.Main) {
                        if (count != 0)
                            setCurrentItem(index++ % count, true)
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
        _boxOfficeAdapter = BoxOfficeAdapter(openDetailsWebView)
        binding.recyclerViewTopBoxOffice.apply {
            adapter = boxOfficeAdapter
            layoutManager =
                LinearLayoutManager(context).apply {
                    orientation = RecyclerView.VERTICAL
                }
            setHasFixedSize(true)
        }
    }

    private val urlWeb = "https://www.imdb.com/title/"
    private val openDetailsWebView: (String) -> Unit = { id ->
        val action = HomeFragmentDirections.actionNavigationHomeToWebViewFragment(urlWeb.plus(id))
        findNavController().navigate(action)
    }

    private val openTrailer: (String) -> Unit = { linkWeb ->
        val action = HomeFragmentDirections.actionNavigationHomeToWebViewFragment(linkWeb)
        findNavController().navigate(action)
    }
    private val openPoster: (String) -> Unit = { id ->
        val action = HomeFragmentDirections.actionNavigationHomeToWebViewFragment((urlWeb.plus(id)))
        findNavController().navigate(action)
    }

    override fun onStop() {
        super.onStop()
        viewModel.resetSnack()
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