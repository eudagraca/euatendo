package br.com.euatendo.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.euatendo.data.responses.LoginResponse
import br.com.euatendo.data.repository.AuthRepository
import br.com.euatendo.util.Resource
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository,
): ViewModel() {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch{
       _loginResponse.value = authRepository.login(email, password)
    }
}