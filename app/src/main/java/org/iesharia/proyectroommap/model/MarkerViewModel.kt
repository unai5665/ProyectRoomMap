package org.iesharia.proyectroommap.model

import android.app.*
import androidx.lifecycle.*
import kotlinx.coroutines.*
import org.iesharia.proyectroommap.data.AppDatabase
import org.iesharia.proyectroommap.data.Marker
import org.iesharia.proyectroommap.data.MarkerType
import org.iesharia.proyectroommap.data.MarkerWithType


class MarkerViewModel(application: Application) : AndroidViewModel(application) {
    private val markerDao = AppDatabase.getDatabase(application).markerDao()
    private val markerTypeDao = AppDatabase.getDatabase(application).markerTypeDao()

    // Cambiar el tipo de LiveData para que coincida con el tipo devuelto por el DAO
    val allMarkers: LiveData<List<MarkerWithType>> = markerDao.getAllMarkers()

    val allMarkerTypes: LiveData<List<MarkerType>> = markerTypeDao.getAllMarkerTypes()

    fun addMarker(marker: Marker) {
        viewModelScope.launch(Dispatchers.IO) {
            markerDao.insertMarker(marker)
        }
    }

    fun addMarkerType(markerType: MarkerType) {
        viewModelScope.launch(Dispatchers.IO) {
            markerTypeDao.insertMarkerType(markerType)
        }
    }
}
