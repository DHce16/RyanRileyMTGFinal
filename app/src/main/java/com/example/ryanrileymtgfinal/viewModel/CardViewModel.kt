package com.example.ryanrileymtgfinal.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ryanrileyfinalproject.api.CardRepository
import com.example.ryanrileyfinalproject.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

const val TAG = "CardViewModel"
@HiltViewModel
class CardViewModel @Inject constructor(
    private val repository: CardRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    // Using viewModelScope with our exception handler
    private val viewModelSafeScope by lazy {
        viewModelScope + coroutineExceptionHandler
    }

    // For logging errors of the coroutine
    private val coroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e(TAG, "Context: $coroutineContext\nMessage: ${throwable.localizedMessage}", throwable)
        }
    }

    private val _cardList = MutableLiveData<UIState>()
    val cardList: LiveData<UIState> get() = _cardList

    private val _boosterList = MutableLiveData<UIState>()
    val boosterList: LiveData<UIState> get() = _boosterList

    private val _cardDetails = MutableLiveData<UIState>()
    val cardDetails: LiveData<UIState> get() = _cardDetails

    private val  _boosterDetails =MutableLiveData<UIState>()
    val  boosterDetails: LiveData<UIState> get() = _boosterDetails

    fun getCardList(set: String){
        viewModelSafeScope.launch(dispatcher){
            repository.getCardList(set).collect{
                _cardList.postValue(it)
            }
        }
    }

    fun getBoosterList(booster: String, offset: Int){
        viewModelScope.launch {
            repository.getBoosterList(booster, offset).collect{
                _boosterList.postValue(it)
            }
        }
    }

    fun getCardDetails(card: Int){
        viewModelScope.launch {
            repository.getCardDetails(card).collect{
                _cardDetails.postValue(it)
            }
        }
    }

    fun getBoosterDetails(boosterSelected: Int){
        viewModelScope.launch {
            repository.getBoosterDetails(boosterSelected).collect{
                _boosterDetails.postValue(it)
            }
        }
    }

    fun setLoadingState() { _cardList.value = UIState.Loading }
}