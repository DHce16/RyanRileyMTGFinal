package com.example.ryanrileyfinalproject.api

import com.example.ryanrileyfinalproject.model.UIState
import io.magicthegathering.kotlinsdk.api.MtgSetApiClient
import io.magicthegathering.kotlinsdk.model.card.MtgCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

interface CardRepository{
    suspend fun getCardList(set: String): Flow<UIState>
    suspend fun getCardDetails(card: Int): Flow<UIState>
    suspend fun getBoosterList(booster: String): Flow<UIState>
    suspend fun getBoosterDetails(boosterSelected: Int): Flow<UIState>
}

class CardRepositoryImpl @Inject constructor(private val cards: Cards) : CardRepository{
    override suspend fun getCardList(set: String): Flow<UIState> =
        flow {
            try {
                // uses MTG SDK
                val setCode: String = "mm2"

                val cardsResponse: Response<List<MtgCard>> =
                    MtgSetApiClient.generateBoosterPackBySetCode(setCode)
                if (cardsResponse.isSuccessful) {
                    val cards = cardsResponse.body()
                    emit(cards?.let {
                        UIState.Success(it)
                    } ?: throw Exception("Null Response"))
                } else {
                    throw Exception("Failed network call")
                }
            } catch (e: Exception) {
                // catch the errors and run this block instead
                emit(UIState.Error(e))
            }
        }

    override suspend fun getCardDetails(card: Int): Flow<UIState> {
        TODO("Not yet implemented")
    }

    override suspend fun getBoosterList(booster: String): Flow<UIState> {
        TODO("Not yet implemented")
    }

    override suspend fun getBoosterDetails(boosterSelected: Int): Flow<UIState> {
        TODO("Not yet implemented")
    }

}