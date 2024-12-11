package org.iesharia.proyectroommap.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DaoMarker {
    @Query("SELECT * FROM Marker")
    fun getAllMarkers(): LiveData<List<MarkerWithType>>  // Devuelve la relación entre Marker y MarkerType




}


@Dao
interface MarkerTypeDao {
    @Query("SELECT * FROM MarkerType")
    fun getAllMarkerTypes(): LiveData<List<MarkerType>>



}
