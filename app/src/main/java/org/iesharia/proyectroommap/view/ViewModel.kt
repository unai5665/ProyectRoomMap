package org.iesharia.proyectroommap.view

import android.app.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.iesharia.proyectroommap.data.AppDatabase
import org.iesharia.proyectroommap.data.Marker
import org.iesharia.proyectroommap.data.MarkerWithType

class MarcadoresViewModel(application: Application) : AndroidViewModel(application) {
    private val marcadorDao = AppDatabase.getDatabase(application).markerDao()
    private val _marcadoresLiveData = MutableLiveData<List<MarkerWithType>>()
    val marcadoresLiveData: LiveData<List<MarkerWithType>> get() = _marcadoresLiveData


    fun cargarMarcadores() {
        viewModelScope.launch(Dispatchers.IO) {
            marcadorDao.getAllMarkers().observeForever { markers ->
                _marcadoresLiveData.postValue(markers)
            }
        }
    }


    fun agregarMarcador(marker: Marker) {
        viewModelScope.launch(Dispatchers.IO) {
            marcadorDao.insertMarker(marker)
        }
    }
}
