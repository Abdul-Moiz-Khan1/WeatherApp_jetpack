package com.example.jetpack_weatherapp.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetpack_weatherapp.Api.weatherModel

@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveResponse(
        response: SavedResponse
    )

    @Query("SELECT * FROM response_table WHERE id = :id")
    fun getCacheModelById(id: Int): SavedResponse?

}