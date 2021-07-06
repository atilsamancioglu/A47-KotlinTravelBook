package com.atilsamancioglu.kotlintravelbook.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.atilsamancioglu.kotlintravelbook.model.Place
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface PlaceDao {
    @Query("SELECT * FROM Place")
    fun getAll(): Flowable<List<Place>>

    @Insert
    fun insert(place: Place): Completable
    //fun insert(place: Place)

    @Delete
    fun delete(place: Place): Completable
    //fun delete(place: Place)

}