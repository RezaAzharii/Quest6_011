package com.example.viewmodelpart2.ui.view.viewmodel

import androidx.lifecycle.ViewModel
import com.example.viewmodelpart2.model.Mahasiswa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MahasiswaViewModel: ViewModel() {
    private val _mhsState = MutableStateFlow(Mahasiswa())
    val mhsStateUi: StateFlow<Mahasiswa> = _mhsState.asStateFlow()

    fun setNim(inputNim: String){
        _mhsState.update {
                stateNim -> stateNim.copy(nim = inputNim)
        }
    }

    fun setNama(inputNama: String){
        _mhsState.update {
                stateNama -> stateNama.copy(nama = inputNama)
        }
    }

    fun setEmail(inputEmail: String){
        _mhsState.update {
                stateEmail -> stateEmail.copy(email = inputEmail)
        }
    }


}