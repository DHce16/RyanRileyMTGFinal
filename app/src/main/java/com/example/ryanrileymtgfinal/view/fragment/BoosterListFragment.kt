package com.example.ryanrileymtgfinal.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.ryanrileyfinalproject.api.Cards
import com.example.ryanrileyfinalproject.model.UIState
import com.example.ryanrileymtgfinal.databinding.FragmentBoosterListBinding
import com.example.ryanrileymtgfinal.model.BoosterData
import com.example.ryanrileymtgfinal.model.BoosterResponse
import com.example.ryanrileymtgfinal.view.controller.BoosterListPageAdapter

class BoosterListFragment: ViewModelFragment() {
    private var _binding: FragmentBoosterListBinding? = null
    private val binding: FragmentBoosterListBinding get() = _binding!!

    private lateinit var boosterListPageAdapter: BoosterListPageAdapter

    private val args: BoosterListFragmentArgs by navArgs()
    private var currentOffset = 0
    private var shouldUpdateList = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBoosterListBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }

    private fun configureObserver() {
        viewModel.boosterList.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Success<*> -> {
                    renderList(it.response as BoosterResponse)
                }
                is UIState.Error -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        tvErrorText.text = it.error.message
                        tvErrorText.visibility = View.VISIBLE
                    }
                }
                is UIState.Loading -> {
                    viewModel.getBoosterList(args.input, offset = currentOffset)
                }
            }
        }
    }

    private fun renderList(response: BoosterResponse) {
        binding.apply {
            pbLoading.visibility = View.GONE

            rvAnimeList.apply {
                // Setting the adapter once
                if (!shouldUpdateList) {
                    boosterListPageAdapter =
                        BoosterListPageAdapter(openBoosterDetails = ::openBoosterDetails)
                    adapter = boosterListPageAdapter
                }
                boosterListPageAdapter.setBoosterList(
                    response.data,
                    shouldUpdateList
                )
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(
                        recyclerView: RecyclerView,
                        dx: Int,
                        dy: Int
                    ) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (!rvAnimeList.canScrollVertically(1)) {
                            shouldUpdateList = true
                            viewModel.getBoosterList(
                                booster = args.input,
                                currentOffset + Cards.NUMBER_OF_ITEMS
                            )
                            Toast.makeText(
                                context,
                                "Loading more Boosters",
                                Toast.LENGTH_SHORT
                            ).show()
                            currentOffset += Cards.NUMBER_OF_ITEMS
                        }
                    }
                })
            }
        }
    }

    private fun openBoosterDetails(node: BoosterData) {

    }
}