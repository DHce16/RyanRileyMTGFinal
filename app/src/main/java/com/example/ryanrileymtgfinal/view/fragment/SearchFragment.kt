package com.example.ryanrileyfinalproject.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    fun getBoosters() {

    }
}