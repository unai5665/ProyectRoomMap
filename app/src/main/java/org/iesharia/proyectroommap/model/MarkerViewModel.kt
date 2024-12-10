package org.iesharia.proyectroommap.model

import android.app.*
import androidx.lifecycle.*
import kotlinx.coroutines.*
import org.iesharia.proyectroommap.data.AppDatabase
import org.iesharia.proyectroommap.data.Marker
import org.iesharia.proyectroommap.data.MarkerType


class MarkerViewModel(application: Application) : AndroidViewModel(application) {
    private val markerDao = AppDatabase.getDatabase(application).markerDao()
    private val markerTypeDao = AppDatabase.getDatabase(application).markerTypeDao()

    val allMarkers: LiveData<List<Marker>> = markerDao.getAllMarkers()
    val allMarkerTypes: LiveData<List<MarkerType>> = markerTypeDao.getAllMarkerTypes()

    fun addMarker(marker: Marker) {
        viewModelScope.launch(Dispatchers.IO) {
            markerDao.insertMarker(marker)
        }
    }


}
