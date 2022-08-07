package com.example.ryanrileymtgfinal.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class BoosterResponse(
    val data: List<BoosterData>,
    val paging: Paging
)

data class Paging(
    val next: String
)

@Parcelize
data class BoosterData(
    val name: String?
) : Parcelable