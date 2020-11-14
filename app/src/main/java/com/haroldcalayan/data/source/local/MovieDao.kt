package com.haroldcalayan.data.source.local

import androidx.room.*
import com.haroldcalayan.data.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    suspend fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movie LIMIT :limit OFFSET :offset")
    suspend fun getSomeMovies(limit: Int, offset: Int): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMovie(movie: Movie)

    @Query("DELETE FROM movie")
    suspend fun deleteAll()
}