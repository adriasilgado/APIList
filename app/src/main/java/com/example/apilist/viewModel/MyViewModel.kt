package com.example.apilist.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apilist.api.Repository
import com.example.apilist.model.Agente
import com.example.apilist.model.Data
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
    private val _agent = MutableLiveData<Agente>()
    val agent = _agent
    private val _isFavourite = MutableLiveData(false)
    val isFavourite = _isFavourite
    private val _favourites = MutableLiveData<MutableList<Data>>()
    val favourites = _favourites
    private val _searchText = MutableLiveData<String>()
    val searchText = _searchText
    private val _charactersAPI = MutableLiveData<ValorantAgentes>()
    val charactersAPI = _charactersAPI
    private val _esconder = MutableLiveData<Boolean>()
    val esconder = _esconder

    fun getCharacters(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getAllCharacters()
            withContext(Dispatchers.Main) {
                if(response.isSuccessful){
                    _characters.value = response.body()
                    _charactersAPI.value = _characters.value
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
                    _agent.value = response.body()
                    _loading.value = false
                }
                else{
                    Log.e("Error :", response.message())
                }
            }
        }
    }

    fun getFavourites() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getFavourites()
            withContext(Dispatchers.Main) {
                _favourites.value = response
                _loading.value = false
            }
        }
    }

    fun isFavorite(agente: Data) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.isFavourite(agente)
            withContext(Dispatchers.Main) {
                _isFavourite.value = response
            }
        }
    }

    fun saveAsFavourite(agente: Data) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.saveAsFavourite(agente)
        }
    }

    fun deleteFavourite(agente: Data) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteFavourite(agente)
        }
    }

    fun changeFavourite() {
        _isFavourite.value = !_isFavourite.value!!
    }

    fun onSearchTextChange(nom:String) {
        _searchText.value = nom
        var agentesFiltrados =
            ValorantAgentes(_charactersAPI.value!!.data.filter { it.displayName.lowercase().contains(nom.lowercase())}, 0)
        _characters.value = agentesFiltrados
        if (nom.isEmpty()) _characters.value = _charactersAPI.value
    }

    fun changeEsconder(newEsconder:Boolean) {
        _esconder.value = newEsconder != true
    }
}