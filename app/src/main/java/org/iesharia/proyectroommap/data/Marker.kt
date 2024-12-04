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

