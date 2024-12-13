package org.iesharia.proyectroommap.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Marker::class, MarkerType::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun markerDao(): DaoMarker
    abstract fun markerTypeDao(): MarkerTypeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "marker_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
