package com.tatianepinto.mvvm_kotlin_1valet.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tatianepinto.mvvm_kotlin_1valet.model.Device

@Database(entities = [Device::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun deviceDao(): DevicesDAO

    companion object {

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "appDevices_database"
                )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}