package com.ahmed_abdallah.pop_flake.ui.settting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ahmed_abdallah.pop_flake.R
import com.ahmed_abdallah.pop_flake.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val settingViewModel =
            ViewModelProvider(this).get(SettingViewModel::class.java)

        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initExposeMenu()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    private fun initExposeMenu() {
        val themes = resources.getStringArray(R.array.themes)
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, themes)
        binding.autoText.apply {
            setAdapter(adapter)
            setOnItemClickListener { _, _, position, _ ->
                Toast.makeText(requireContext(), "$position", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}