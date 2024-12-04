package org.iesharia.proyectroommap.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface DaoMarker{
    @Query("SELECT * FROM Marker")
    fun getAllMarkers(): LiveData<List<Marker>>

    @Insert
    suspend fun insertMarker(marker: Marker)

    @Delete
    suspend fun deleteMarker(marker: Marker)

}