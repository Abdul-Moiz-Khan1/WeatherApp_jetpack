package com.example.jetpack_weatherapp.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import retrofit2.Converter

@Database(entities = [SavedResponse::class], version = 1 , exportSchema = false)
abstract class ResponseDatabase:RoomDatabase() {
    abstract fun dao():RoomDao
}