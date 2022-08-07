package com.example.ryanrileymtgfinal.view.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ryanrileymtgfinal.R
import com.example.ryanrileymtgfinal.databinding.BoosterListItemBinding
import com.example.ryanrileymtgfinal.model.BoosterData

class BoosterListPageAdapter(
    private val boosterList: MutableList<BoosterData> = mutableListOf(),
    private val openBoosterDetails: (BoosterData) -> Unit
) : RecyclerView.Adapter<BoosterListPageAdapter.BoosterViewHolder>() {

    fun setBoosterList(newList: List<BoosterData>, updateList: Boolean) {
        if (updateList) {
            boosterList.addAll(newList)
            notifyItemRangeChanged(0, itemCount)
        } else {
            boosterList.clear()
            boosterList.addAll(newList)
            // works like notifySetChanged, but without the warning
            notifyItemRangeChanged(0, itemCount)
        }
    }

    inner class BoosterViewHolder(
        private val binding: BoosterListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: BoosterData) {
            binding.apply {

                tvBoosterListName.text = data.name
            Glide.with(ivBoosterListImage)
                .load(R.drawable.magic_icon)
                .into(binding.ivBoosterListImage)

            binding.btnBoosterListDetails.setOnClickListener {
                openBoosterDetails(data)
            }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BoosterViewHolder(
            BoosterListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BoosterViewHolder, position: Int) {
        holder.onBind(boosterList[position])
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}