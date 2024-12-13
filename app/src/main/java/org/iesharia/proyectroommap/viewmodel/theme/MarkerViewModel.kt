package org.iesharia.proyectroommap.viewmodel.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.viewmodelrm.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.iesharia.proyectroommap.model.AppDatabase
import org.iesharia.proyectroommap.model.Marker
import org.iesharia.proyectroommap.model.MarkerType
import org.iesharia.proyectroommap.model.MarkerWithType

class MarkerViewModel(application: Application) : AndroidViewModel(application) {
    private val markerDao = AppDatabase.getDatabase(application).markerDao()
    private val markerTypeDao = AppDatabase.getDatabase(application).markerTypeDao()

    val markersWithTypes: LiveData<List<MarkerWithType>> = markerDao.getAllMarkers()

    // Nueva función para inicializar datos
    fun initializeData() = viewModelScope.launch(Dispatchers.IO) {
        // Verificar si ya existen tipos de marcadores en la base de datos
        val existingMarkerTypes = markerTypeDao.getAllMarkerTypesSync() // Método síncrono

        // Si no existen los tipos de marcadores, los insertamos
        if (existingMarkerTypes.isEmpty()) {
            val defaultMarkerTypes = listOf(
                MarkerType(name = "BurgerKing's", iconResource = R.drawable.burger_king),
                MarkerType(name = "Café", iconResource = R.drawable.cafeteria),
                MarkerType(name = "Parque", iconResource = R.drawable.parque_natural),
                MarkerType(name = "Museo", iconResource = R.drawable.museo_britanico)
            )
            defaultMarkerTypes.forEach { markerTypeDao.insertMarkerType(it) }
        }

        // Verificar si ya existen los marcadores en la base de datos
        val existingMarkers = markerDao.getAllMarkersSync() // Método síncrono

        if (existingMarkers.isEmpty()) {
            // Marcadores predeterminados
            val defaultMarkers = listOf(
                Marker(title = "Burger King", latitude = 40.70974178447033, longitude = -74.01187432862362, typeId = 1),
                Marker(title = "Burger King", latitude = 40.75076083238586, longitude = -73.98823646870025, typeId = 1),
                Marker(title = "Burger King", latitude = 40.66425998295402, longitude = -73.95697833985417, typeId = 1),
                Marker(title = "Burger King", latitude = 40.610526374846245, longitude = -73.96228524826718, typeId = 1),

                Marker(title = "Now or Never Coffee", latitude = 40.72376943671599, longitude = -74.00474951958478, typeId = 2),
                Marker(title = "Café De Novo", latitude = 40.70815511344941, longitude = -74.01366922364346, typeId = 2),
                Marker(title = "Café Lyria", latitude = 40.70930967479926, longitude = -74.0078811274041, typeId = 2),
                Marker(title = "Café Atelier", latitude = 40.719603235665225, longitude = -74.00837475829182, typeId = 2),

                Marker(title = "Battery Park", latitude = 40.70298868168502, longitude = -74.01534212907517, typeId = 3),
                Marker(title = "South Cove Park", latitude = 40.70709413109254, longitude = -74.0183582110148, typeId = 3),
                Marker(title = "Parque Libertad", latitude = 40.710496924215086, longitude = -74.0138536326892, typeId = 3),
                Marker(title = "Parque Rockefeller", latitude = 40.71635306716272, longitude = -74.01673969600738, typeId = 3),

                Marker(title = "Museum at Eldridge Street", latitude = 40.714782416952566, longitude = -73.99351211062873, typeId = 4),
                Marker(title = "Museum of Illusions - New York", latitude = 40.739948580548, longitude = -74.00305382169066, typeId = 4),
                Marker(title = "Madame Tussauds New York", latitude = 40.757003103857066, longitude = -73.98891734730861, typeId = 4),
                Marker(title = "SPYSCAPE", latitude = 40.76559718382199, longitude = -73.98386440453723, typeId = 4)
            )
            defaultMarkers.forEach { markerDao.insertMarker(it) }
        }
    }
}
