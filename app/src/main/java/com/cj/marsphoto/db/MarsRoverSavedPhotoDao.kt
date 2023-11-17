package com.cj.marsphoto.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MarsRoverSavedPhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(marsRoverSavedLocalModel: MarsRoverSavedLocalModel)

    @Delete
    suspend fun delete(marsRoverSavedLocalModel: MarsRoverSavedLocalModel)


    @Query("SELECT roverPhotoId FROM rover_photo WHERE sol = :sol AND roverName = :roverName")
    fun allSavedIds(sol: String, roverName: String): kotlinx.coroutines.flow.Flow<List<Int>>

    @Query("SELECT * FROM rover_photo ORDER By earthDate DESC")
    fun getAllSaved():kotlinx.coroutines.flow.Flow<List<MarsRoverSavedLocalModel>>

}