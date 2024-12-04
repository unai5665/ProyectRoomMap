package org.iesharia.proyectroommap.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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