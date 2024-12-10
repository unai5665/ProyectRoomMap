package org.iesharia.proyectroommap.data

import androidx.room.*

@Entity
data class MarkerType(
    @PrimaryKey(autoGenerate = true) val id: Int,
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
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val latitude: Double,
    val longitude: Double,
    val typeId: Int
)

data class MarkerWithType(
    @Embedded val marker: Marker,
    @Relation(parentColumn = "typeId", entityColumn = "id") val markerType: MarkerType
)