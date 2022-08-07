package com.example.ryanrileymtgfinal.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ryanrileymtgfinal.databinding.FragmentSearchBinding

class SearchFragment: Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(layoutInflater)

        binding.btnStartSearch.setOnClickListener {
            getBoosters()
        }
        return binding.root
    }

    private fun getBoosters() {
//        viewModel.setLoadingState()

        findNavController().navigate(
            SearchFragmentDirections.actionNavSearchToBoosterList(
                "sets"// CharSequence -> String
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}