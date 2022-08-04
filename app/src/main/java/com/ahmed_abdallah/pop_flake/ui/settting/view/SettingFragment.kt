package com.ahmed_abdallah.pop_flake.ui.settting.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmed_abdallah.pop_flake.R
import com.ahmed_abdallah.pop_flake.Utils.ResultState
import com.ahmed_abdallah.pop_flake.databinding.FragmentSettingBinding
import com.ahmed_abdallah.pop_flake.ui.form.sharedViewModel.FormViewModel
import com.ahmed_abdallah.pop_flake.ui.home.adapter.ComingAdapter
import com.ahmed_abdallah.pop_flake.ui.settting.adapter.ComplainsAdapter
import com.ahmed_abdallah.pop_flake.ui.settting.viewModel.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var _adapter: ArrayAdapter<String>? = null
    private val adapter get() = _adapter!!

    private var _complainAdapter: ComplainsAdapter? = null
    private val complainAdapter get() = _complainAdapter!!

    private val viewModel: SettingViewModel by viewModels()
    private val sharedViewModel: FormViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view()
    }

    private fun view() {
        viewModel.getMode()
        initExposeMenu()
        handleMode()
        handleComplainList()
        initComplainRecycler()
        handleAnyComplain()
    }

    private fun handleAnyComplain() {
        binding.txtAnyComplain.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_notifications_to_formFragment)
        }
    }

    private fun initComplainRecycler() {
        _complainAdapter = ComplainsAdapter()
        binding.recycleComplains.apply {
            adapter = _complainAdapter
            layoutManager =
                LinearLayoutManager(context).apply {
                    orientation = RecyclerView.VERTICAL
                }
            setHasFixedSize(true)
        }

    }

    private fun handleComplainList() {
        sharedViewModel.getComplains()
        lifecycleScope.launchWhenStarted {
            sharedViewModel.complains.collect { result ->
                when (result) {

                    is ResultState.Success -> {
                        complainAdapter.setComplainList(result.data)
                    }
                }
            }
        }
    }


    private fun handleMode() {
        viewModel.mode.observe(viewLifecycleOwner) { mode ->
            when (mode) {
                1 -> binding.autoText.setText(adapter.getItem(0), false)
                2 -> binding.autoText.setText(adapter.getItem(1), false)
            }
        }
    }

    private fun initExposeMenu() {
        val themes = resources.getStringArray(R.array.themes)
        _adapter = ArrayAdapter(requireContext(), R.layout.list_item, themes)
        binding.autoText.apply {
            setAdapter(this@SettingFragment.adapter)
            setOnItemClickListener { _, _, position, _ ->
                viewModel.setMode(position + 1)
                requireActivity().finish()
                requireActivity().startActivity(requireActivity().intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _adapter = null
        _complainAdapter = null
    }
}