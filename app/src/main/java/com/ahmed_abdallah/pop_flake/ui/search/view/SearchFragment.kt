package com.ahmed_abdallah.pop_flake.ui.search.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmed_abdallah.pop_flake.R
import com.ahmed_abdallah.pop_flake.Utils.ResultState
import com.ahmed_abdallah.pop_flake.databinding.FragmentSearchBinding
import com.ahmed_abdallah.pop_flake.ui.search.adapter.SearchAdapter
import com.ahmed_abdallah.pop_flake.ui.search.viewModel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.buffer

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var _searchAdapter: SearchAdapter? = null
    private val searchAdapter get() = _searchAdapter!!

    private val viewModel: SearchViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        initSearchRecycler()
        handleSearchResults()
        //circular
    }

    private fun handleSearchResults() {
        lifecycleScope.launchWhenStarted {
            viewModel.resultList.buffer().collect { result ->
                when (result) {
                    ResultState.EmptyResult -> {
                        handleViewsVisibility(false)
                        searchAdapter.setSearchMovieList(emptyList())
                    }
                    is ResultState.Error -> {
                        handleViewsVisibility(false)
                    }
                    ResultState.Loading -> {
                        handleViewsVisibility(true)
                    }
                    is ResultState.Success -> {
                        handleViewsVisibility(false)
                        searchAdapter.setSearchMovieList(result.data)
                    }
                }
            }
        }
    }

    private fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {

        val query = MutableStateFlow("")

        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                query.value = newText
                return true
            }
        })

        return query

    }

    private fun handleViewsVisibility(visible: Boolean) {
        val progressVisibility = if (visible) View.VISIBLE else View.GONE
        val otherViewVisibility = if (visible) View.INVISIBLE else View.VISIBLE
        with(binding) {
            progressCircular.visibility = progressVisibility
            recyclerSearch.visibility = otherViewVisibility

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.opt_menu, menu)
        val searchView = menu.findItem(R.id.search)?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_title)
        viewModel.searchForSeriesOrMovies(searchView.getQueryTextChangeStateFlow())
        onCloseSearch(searchView)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    private fun onCloseSearch(searchView: SearchView) {
        searchView.setOnCloseListener {
            viewModel.closeSearch()
            return@setOnCloseListener false
        }
    }


    private fun initSearchRecycler() {
        _searchAdapter = SearchAdapter()
        binding.recyclerSearch.apply {
            adapter = searchAdapter
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
        _searchAdapter = null
    }
}