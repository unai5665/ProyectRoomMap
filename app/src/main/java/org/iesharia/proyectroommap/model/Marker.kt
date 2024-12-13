package org.iesharia.proyectroommap.model

import androidx.room.*

@Entity
data class MarkerType(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // No es necesario pasar 'id' al crear
    val name: String,
    val iconResource: Int
)

@Entity(
    foreignKeys = [ForeignKey(
        entity = MarkerType::class,
        parentColumns = ["id"],
        childColumns = ["typeId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Marker(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // No es necesario pasar 'id' al crear
    val title: String,
    val latitude: Double,
    val longitude: Double,
    val typeId: Int
)

data class MarkerWithType(
    @Embedded val marker: Marker,
    @Relation(parentColumn = "typeId", entityColumn = "id") val markerType: MarkerType
)
