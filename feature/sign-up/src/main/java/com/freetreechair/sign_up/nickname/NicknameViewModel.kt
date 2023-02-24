package com.freetreechair.sign_up.nickname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.freetreechair.common.base.BaseViewModel
import com.freetreechair.domain.signup.nickname.usecase.CheckIsNicknameDuplicatedUseCase
import com.freetreechair.domain.signup.nickname.usecase.NicknameUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import com.freetreechair.sign_up.nickname.NicknameState.DUPLICATE_NICKNAME
import com.freetreechair.sign_up.nickname.NicknameState.ALLOWED_NICKNAME

@HiltViewModel
class NicknameViewModel @Inject constructor(
    private val nicknameUseCases: NicknameUseCases
) : BaseViewModel() {

    var nickname = MutableLiveData("")

    private val _nicknameState = MutableLiveData<NicknameState>()
    val nicknameState: LiveData<NicknameState> = _nicknameState

    fun checkDuplicateNickname() {
        viewModelScope.launch {
            nicknameUseCases.checkIsNicknameDuplicatedUseCase(nickname.value.orEmpty())
                .onSuccess {
                    if (it) {
                        _nicknameState.postValue(ALLOWED_NICKNAME)
                        nicknameUseCases.saveNicknameUseCase(nickname.value.orEmpty())
                    } else {
                        _nicknameState.postValue(DUPLICATE_NICKNAME)
                    }
                }
                .onFailure {
                    Timber.d(it)
                }
        }
    }

    fun updateNicknameState(state: NicknameState) {
        _nicknameState.value = state
    }
}
