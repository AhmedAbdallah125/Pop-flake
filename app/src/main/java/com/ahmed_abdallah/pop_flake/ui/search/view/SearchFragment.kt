package com.ahmed_abdallah.pop_flake.ui.search.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmed_abdallah.pop_flake.R
import com.ahmed_abdallah.pop_flake.databinding.FragmentSearchBinding
import com.ahmed_abdallah.pop_flake.ui.search.adapter.SearchAdapter
import com.ahmed_abdallah.pop_flake.ui.search.viewModel.SearchViewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var _searchAdapter: SearchAdapter? = null
    private val searchAdapter get() = _searchAdapter!!

    private val searchViewModel: SearchViewModel by viewModels()
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
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.opt_menu, menu)
        val searchView = menu.findItem(R.id.search)?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_title)
        onQueryTextListener(searchView)
        onCloseSearch(searchView)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    private fun onCloseSearch(searchView: SearchView) {
//        searchView.setOnCloseListener {
////            homeViewModel.getBrandsAgain()
////            return@setOnCloseListener false
//        }
    }

    private fun onQueryTextListener(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
//                newText?.let {
//                    homeViewModel.setSearchQuery(it.trim())
//                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
//                query?.let {
//                    homeViewModel.setSearchQuery(query.trim())
//                }
                return false
            }
        })
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