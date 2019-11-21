package com.atruskova.itunesapitesttask.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class Album (
    @SerializedName("collectionId")
    @PrimaryKey
    var ID : String,
    @SerializedName("wrapperType")
    var WrapperType: String,
    @SerializedName("collectionType")
    var CollectionType: String,
    @SerializedName("artistId")
    var ArtistID: Long?,
    @SerializedName("amgArtistId")
    var AmgArtistId: Long?,
    @SerializedName("artistName")
    var ArtistName: String?,
    @SerializedName("collectionName")
    var CollectionName: String,
    @SerializedName("collectionCensoredName")
    var CollectionCensoredName: String?,
    @SerializedName("artistViewUrl")
    var ArtistViewUrl: String?,
    @SerializedName("collectionViewUrl")
    var CollectionViewUrl: String?,
    @SerializedName("artworkUrl60")
    var ArtWorkUrl60: String?,
    @SerializedName("artworkUrl100")
    var ArtWorkUrl100: String?,
    @SerializedName("collectionPrice")
    var CollectionPrice: String?,
    @SerializedName("collectionExplicitness")
    var CollectionExplictness: String?,
    @SerializedName("trackCount")
    var TrackCount: Int?,
    @SerializedName("copyright")
    var Copyright: String?,
    @SerializedName("country")
    var Country: String?,
    @SerializedName("currency")
    var Currency: String?,
    @SerializedName("releaseDate")
    var ReleaseDate: Date?,
    @SerializedName("primaryGenreName")
    var PrimaryGenreName: String?
)