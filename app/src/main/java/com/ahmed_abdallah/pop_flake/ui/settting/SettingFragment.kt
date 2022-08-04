package com.ahmed_abdallah.pop_flake.ui.settting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ahmed_abdallah.pop_flake.R
import com.ahmed_abdallah.pop_flake.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var _adapter: ArrayAdapter<String>? = null
    private val adapter get() = _adapter!!

    private val viewModel: SettingViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//https://www.imdb.com/title/%7Bmovie_id%7D
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getMode()
        initExposeMenu()
        handleMode()
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
//                AppCompatDelegate.setDefaultNightMode(position+1)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _adapter = null
    }
}