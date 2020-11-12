package com.haroldcalayan.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
class Movie (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val wrapperType: String?,
    val artistId: Int,
    val collectionId: Long,
    val artistName: String?,
    val collectionName: String?,
    val collectionCensoredName: String?,
    val artistViewUrl: String?,
    val collectionViewUrl: String?,
    val artworkUrl60: String?,
    val artworkUrl100: String?,
    val collectionPrice: Float,
    val collectionExplicitness: String?,
    val trackCount: Int,
    val copyright: String?,
    val country: String?,
    val currency: String?,
    val releaseDate: String?,
    val primaryGenreName: String?,
    val previewUrl: String?,
    val description: String?
)