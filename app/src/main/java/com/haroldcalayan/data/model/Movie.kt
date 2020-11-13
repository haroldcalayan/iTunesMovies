package com.haroldcalayan.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
class Movie (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val wrapperType: String?,
    val kind: String?,
    val artistId: Int,
    val trackId: Long,
    val collectionId: Long,
    val artistName: String?,
    val collectionName: String?,
    val trackName: String?,
    val collectionCensoredName: String?,
    val artistViewUrl: String?,
    val collectionViewUrl: String?,
    val trackViewUrl: String?,
    val previewUrl: String?,
    val artworkUrl30: String?,
    val artworkUrl60: String?,
    val artworkUrl100: String?,
    val collectionPrice: Float,
    val trackPrice: Float,
    val collectionExplicitness: String?,
    val trackExplicitness: String?,
    val discCount: Int,
    val discNumber: Int,
    val trackCount: Int,
    val trackNumber: Int,
    val trackTimeMillis: Int,
    val copyright: String?,
    val country: String?,
    val currency: String?,
    val releaseDate: String?,
    val primaryGenreName: String?,
    val description: String?,
    val isStreamable: Boolean?,
    val contentAdvisoryRating: String?,
    val shortDescription: String?,
    val longDescription: String?,
    val hasITunesExtras: Boolean?
)