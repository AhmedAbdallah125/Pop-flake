package com.ahmed_abdallah.pop_flake.ui.form.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ahmed_abdallah.pop_flake.Utils.showSnack
import com.ahmed_abdallah.pop_flake.Utils.trimText
import com.ahmed_abdallah.pop_flake.databinding.FragmentFormBinding
import com.ahmed_abdallah.pop_flake.pojo.Complain
import com.ahmed_abdallah.pop_flake.ui.form.sharedViewModel.FormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormFragment : Fragment() {
    private var _binding: FragmentFormBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val sharedViewModel: FormViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        submitComplain()
        handleSubmit()
    }

    private fun submitComplain() {
        binding.btnSumit.setOnClickListener {
            sharedViewModel.submitComplain(
                Complain(
                    binding.edName.trimText(),
                    binding.edtComplain.trimText()
                )
            )
        }
    }

    private fun handleSubmit() {
        lifecycleScope.launchWhenStarted {
            sharedViewModel.submitState.collect {
                when (it) {
                    is FormViewModel.AuthResult.InvalidData -> {
                        handleFailureSubmit(it)
                    }
                    FormViewModel.AuthResult.SubmitSuccess -> {
                        handleSuccessSubmit()
                    }
                }
            }
        }
    }

    private fun handleFailureSubmit(it: FormViewModel.AuthResult.InvalidData) {
        when (it.error) {
            FormViewModel.ErrorType.NameError -> binding.edName.error =
                "must submit name"
            FormViewModel.ErrorType.ComplainError -> binding.edtComplain.error =
                "must submit complain"
        }
    }

    private fun handleSuccessSubmit() {
        sharedViewModel.addComplain(
            Complain(
                binding.edName.trimText(),
                binding.edtComplain.trimText()
            )
        )
        binding.edName.setText("")
        binding.edtComplain.setText("")
        showSnack("Your complain is submitted successfully")
        sharedViewModel.resetComplain()
        findNavController().popBackStack()
    }

    override fun onStop() {
        super.onStop()
        sharedViewModel.resetComplain()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}