package com.example.animatedbottomsheet.ui.intro.viewmodel

import com.example.animatedbottomsheet.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor() : BaseViewModel() {

    private val _introState = MutableStateFlow(0)
    val introState = _introState.asStateFlow()

    private val _stepOneVisibility = MutableStateFlow(false)
    val stepOneVisibility = _stepOneVisibility.asStateFlow()

    private val _stepTwoVisibility = MutableStateFlow(false)
    val stepTwoVisibility = _stepTwoVisibility.asStateFlow()

    private val _stepThreeVisibility = MutableStateFlow(false)
    val stepThreeVisibility = _stepThreeVisibility.asStateFlow()

    fun moveNextState(){
        _introState.value += 1
        when(_introState.value){
            1 -> {
                _stepOneVisibility.value = true
            }
            2 -> {
                _stepTwoVisibility.value = true
            }
            3 -> {
                _stepThreeVisibility.value = true
            }
        }
    }

}