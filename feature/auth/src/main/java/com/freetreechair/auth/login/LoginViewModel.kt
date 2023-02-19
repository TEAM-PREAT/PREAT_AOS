package com.freetreechair.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freetreechair.common.util.Event
import com.freetreechair.domain.login.model.DomainLoginRequest
import com.freetreechair.domain.login.usecase.LoginUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases
) : ViewModel() {

    private lateinit var platform: String

    private val _socialToken = MutableLiveData<String>()
    val socialToken: LiveData<String> = _socialToken

    private val _navigateToHome = MutableLiveData<Event<Boolean>>()
    val navigateToHome: LiveData<Event<Boolean>> = _navigateToHome

    private val _navigateToSignUp = MutableLiveData<Event<Boolean>>()
    val navigateToSignUp: LiveData<Event<Boolean>> = _navigateToSignUp

    private val _loginFailureMessage = MutableLiveData<String>()
    val loginFailureMessage: LiveData<String> = _loginFailureMessage

    fun postLogin() {
        viewModelScope.launch {
            loginUseCases.makeLoginRequestUseCase(
                DomainLoginRequest(
                    socialToken = socialToken.value.orEmpty(),
                    platform = platform
                )
            ).onSuccess {
                if (it.isNewUser) {
                    _navigateToSignUp.postValue(Event(true))
                } else {
                    _navigateToHome.postValue(Event(true))
                }
            }.onFailure {
                _loginFailureMessage.postValue(it.message)
            }
        }
    }

    fun updateSocialToken(socialToken: String) {
        _socialToken.value = socialToken
    }

    fun updatePlatform(platform: String) {
        this.platform = platform
    }

    fun getAccessToken(): String {
        return loginUseCases.getAccessTokenUseCase()
    }
}
