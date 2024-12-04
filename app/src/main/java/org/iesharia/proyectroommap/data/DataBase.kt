package org.iesharia.proyectroommap.data

import android.content.Context
import androidx.room.*




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
