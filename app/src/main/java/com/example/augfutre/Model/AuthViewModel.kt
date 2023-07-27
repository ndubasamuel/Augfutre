package com.example.augfutre.Model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.augfutre.Model.ModelUseCase.LoginUseCase
import com.example.augfutre.Model.ModelUseCase.State.TextFieldState
import com.example.augfutre.Model.ModelUseCase.State.UiEvents
import com.example.augfutre.Utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor (
    private val loginUseCase: LoginUseCase
        ): ViewModel() {
            private var _loginState = mutableStateOf(AuthState())
    val loginState: State<AuthState> = _loginState

    private val _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _firstName = mutableStateOf(TextFieldState())
    val firstName: State<TextFieldState> = _firstName

    fun setFirstName(value:String){
        _firstName.value = firstName.value.copy(text = value)
    }
    private val _lastName = mutableStateOf(TextFieldState())
    val lastName: State<TextFieldState> = _lastName

    fun setLastName(value:String){
        _lastName.value = lastName.value.copy(text = value)
    }
    private val _emailState = mutableStateOf(TextFieldState())
    val emailState: State<TextFieldState> = _emailState

    fun setEmail(value:String) {
        _emailState.value = emailState.value.copy(text = value)
    }
    private val _passwordState = mutableStateOf(TextFieldState())
    val passwordState: State<TextFieldState> = _passwordState

    fun setPassword(value:String){
        _passwordState.value = passwordState.value.copy(text = value)
    }
    fun loginUser(){
        viewModelScope.launch {
            _loginState.value = loginState.value.copy(isLoading = false)

            val loginResult = loginUseCase(
                email = emailState.value.text,
                password = passwordState.value.text
            )

            _loginState.value = loginState.value.copy(isLoading = false)

            if (loginResult.emailError != null){
                _emailState.value=emailState.value.copy(error = loginResult.emailError)
            }
            if (loginResult.passwordError != null){
                _passwordState.value = passwordState.value.copy(error = loginResult.passwordError)
            }

            when(loginResult.result){
                is Resource.Success->{
                    _eventFlow.emit(
                        UiEvents.NavigateEvent("")
                    )
                }
                is Resource.Error->{
                    UiEvents.SnackbarEvent(
                        loginResult.result.message ?: "Error!"
                    )
                }
                else -> {

                }
            }
        }
    }

}