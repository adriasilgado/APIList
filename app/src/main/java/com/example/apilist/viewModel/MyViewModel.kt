package com.example.apilist.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apilist.api.Repository
import com.example.apilist.model.Data
import com.example.apilist.model.Role
import com.example.apilist.model.ValorantAgentes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel: ViewModel() {
    private val repository = Repository()
    private val _loading = MutableLiveData(true)
    val loading = _loading
    private val _characters = MutableLiveData<ValorantAgentes>()
    val characters = _characters
    private val _agent = MutableLiveData<Data>()
    var agent = _agent

    fun getCharacters(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getAllCharacters()
            withContext(Dispatchers.Main) {
                if(response.isSuccessful){
                    _characters.value = response.body()
                    _loading.value = false
                }
                else{
                    Log.e("Error :", response.message())
                }
            }
        }
    }

    fun getCharacter(uuid:String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getCharacter(uuid)
            withContext(Dispatchers.Main) {
                if(response.isSuccessful){
                    agent.value = response.body()
                    _loading.value = false
                    println("Character Details UUID: ${response.body()?.uuid}, Nombre: ${response.body()?.displayName}")
                }
                else{
                    Log.e("Error :", response.message())
                }
            }
        }
    }

}