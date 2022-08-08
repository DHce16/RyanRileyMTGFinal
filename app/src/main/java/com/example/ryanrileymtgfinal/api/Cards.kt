package com.example.ryanrileyfinalproject.api

import com.example.ryanrileymtgfinal.model.BoosterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Cards {

    @GET(NAME)
    suspend fun getBoosterList(
        @Path(SETS) sets: String = "sets",
        @Query(LIMIT) limit: Int = NUMBER_OF_ITEMS,
        @Query(OFFSET) offset: Int = 0
    ): Response<BoosterResponse>

    @GET(CODE)
    suspend fun getCardList(
        @Path(SETS) sets: String,
        @Path(NAME) name: Int,
        @Path(BOOSTER) booster: String
    ): Response<BoosterResponse>

    companion object {
        /*
        full anime list url ->
        full details url ->
         */
        const val BASE_URL = "https://api.magicthegathering.io/v1/"
        const val NAME = "name"
        const val BOOSTER = "booster"
        const val SETS = "sets"
        const val Q = "q"
        const val LIMIT = "limit"
        const val OFFSET = "offset"
        const val NUMBER_OF_ITEMS = 40
        const val CODE = "code"
    }
}