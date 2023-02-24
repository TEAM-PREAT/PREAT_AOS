package com.freetreechair.sign_up.complete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.freetreechair.common.base.BaseViewModel
import com.freetreechair.common.util.Event
import com.freetreechair.domain.signup.complete.model.DomainSignUpRequest
import com.freetreechair.domain.signup.complete.usecase.CompleteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CompleteViewModel @Inject constructor(
    private val completeUseCases: CompleteUseCases
) : BaseViewModel() {

    private val _nickname = MutableLiveData<String>()
    val nickname: LiveData<String> = _nickname

    private val _spicy = MutableLiveData<Int>()
    val spicy: LiveData<Int> = _spicy

    private val _sweet = MutableLiveData<Int>()
    val sweet: LiveData<Int> = _sweet

    private val _salty = MutableLiveData<Int>()
    val salty: LiveData<Int> = _salty

    private val _disgusts = MutableLiveData<List<Int>>()
    val disgusts: LiveData<List<Int>> = _disgusts

    private val _evaluates = MutableLiveData<String>()
    val evaluates: LiveData<String> = _evaluates

    private val _navigateToHome = MutableLiveData<Event<Boolean>>()
    val navigateToHome: LiveData<Event<Boolean>> = _navigateToHome

    private val _signUpFailureMessage = MutableLiveData<String>()
    val signUpFailureMessage: LiveData<String> = _signUpFailureMessage

    fun getUserIdentification() {
        val userIdentification = completeUseCases.getUserIdentificationUseCase()
        _nickname.value = userIdentification.getOrDefault(PREAT_USER_NICKNAME, EMPTY_VALUE)
        val tastes = userIdentification.getOrDefault(PREAT_USER_TASTES, EMPTY_VALUE)
        if (tastes.isNotEmpty()) {
            tastes.split(",").apply {
                _spicy.value = (this[0].toDouble() * 100).toInt()
                _sweet.value = (this[1].toDouble() * 100).toInt()
                _salty.value = (this[2].toDouble() * 100).toInt()
            }
        }
        val disgusts = userIdentification.getOrDefault(PREAT_USER_DISGUSTS, EMPTY_VALUE)
        if (disgusts.isNotEmpty()) {
            _disgusts.value = disgusts.dropLast(1).split(",").map { it.toInt() }
        }
        val evaluates = userIdentification.getOrDefault(PREAT_USER_EVALUATES, EMPTY_VALUE)
        if (evaluates.isNotEmpty()) {
            _evaluates.value = evaluates.dropLast(1)
        }
        Timber.tag("logging").d(userIdentification.toString())
    }

    fun postSignUp() {
        viewModelScope.launch {
            completeUseCases.makeSignUpRequestUseCase(
                DomainSignUpRequest(
                    nickname = nickname.value.orEmpty(),
                    spicy = spicy.value ?: -1,
                    sweet = sweet.value ?: -1,
                    salty = salty.value ?: -1,
                    disgusts = disgusts.value ?: emptyList(),
                    evaluates = evaluates.value.orEmpty()
                )
            ).onSuccess {
                _navigateToHome.postValue(Event(true))
            }.onFailure {
                _signUpFailureMessage.postValue(it.message)
            }
        }
    }

    companion object {
        const val EMPTY_VALUE = ""
        const val PREAT_USER_NICKNAME = "PREAT_USER_NICKNAME"
        const val PREAT_USER_TASTES = "PREAT_USER_TASTES"
        const val PREAT_USER_DISGUSTS = "PREAT_USER_DISGUSTS"
        const val PREAT_USER_EVALUATES = "PREAT_USER_EVALUATES"
    }
}
