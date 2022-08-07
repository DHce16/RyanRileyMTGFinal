package com.example.ryanrileymtgfinal.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.ryanrileyfinalproject.model.UIState
import com.example.ryanrileymtgfinal.databinding.FragmentBoosterListBinding
import com.example.ryanrileymtgfinal.model.BoosterResponse
import com.example.ryanrileymtgfinal.view.controller.BoosterListPageAdapter

class BoosterListFragment: ViewModelFragment() {
    private var _binding: FragmentBoosterListBinding? = null
    private val binding: FragmentBoosterListBinding get() = _binding!!

    private lateinit var boosterListPageAdapter: BoosterListPageAdapter


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
                    viewModel.getBoosterList(booster = String())
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
                        AnimeListPageAdapter(openAnimeDetails = ::openAnimeDetails)
                    adapter = animeListPageAdapter
                }
                animeListPageAdapter.setAnimeList(
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
                            viewModel.getAnimeList(
                                q = args.input,
                                currentOffset + CrunchyRoll.NUMBER_OF_ITEMS
                            )
                            Toast.makeText(
                                context,
                                "Loading more Boosters",
                                Toast.LENGTH_SHORT
                            ).show()
                            currentOffset += CrunchyRoll.NUMBER_OF_ITEMS
                        }
                    }
                })
            }
        }
    }
}