package org.iesharia.proyectroommap.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DaoMarker {
    @Query("SELECT * FROM Marker")
    fun getAllMarkers(): LiveData<List<MarkerWithType>>  // Devuelve la relación entre Marker y MarkerType


    @Insert(onConflict = OnConflictStrategy.IGNORE)  // Insertará solo si no existe un marcador con esas coordenadas
    suspend fun insertMarker(marker: Marker)

    @Query("DELETE FROM Marker WHERE id NOT IN (SELECT MIN(id) FROM Marker GROUP BY latitude, longitude)")  // Elimina duplicados
    suspend fun deleteDuplicateMarkers()


    @Query("SELECT * FROM Marker")
    fun getAllMarkersSync(): List<Marker>
}


@Dao
interface MarkerTypeDao {
    @Query("SELECT * FROM MarkerType")
    fun getAllMarkerTypes(): LiveData<List<MarkerType>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)  // Insertará solo si no existe ya un tipo igual
    suspend fun insertMarkerType(markerType: MarkerType)

    @Query("DELETE FROM MarkerType WHERE id NOT IN (SELECT MIN(id) FROM MarkerType GROUP BY name)")  // Elimina duplicados
    suspend fun deleteDuplicateMarkerTypes()
    @Query("SELECT * FROM MarkerType")
    fun getAllMarkerTypesSync(): List<MarkerType>
}
