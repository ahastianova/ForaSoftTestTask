package com.atruskova.itunesapitesttask.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
class Song (
    @SerializedName("trackId")
    @PrimaryKey
    var ID : String,
    @SerializedName("wrapperType")
    var WrapperType: String,
    @SerializedName("kind")
    var Kind: String?,
    @SerializedName("artistId")
    var ArtistID: Long?,
    @SerializedName("collectionId")
    var CollectionId : String?,
    @SerializedName("trackName")
    var Name: String?,
    @SerializedName("trackCensoredName")
    var CensoredName: String?,
    @SerializedName("trackViewUrl")
    var TrackViewUrl : String?,
    @SerializedName("previewUrl")
    var PreviewUrl: String?,
    @SerializedName("trackPrice")
    var Price: String?,
    @SerializedName("releaseDate")
    var ReleaseDate : Date?,
    @SerializedName("trackNumber")
    var Number: String?,
    @SerializedName("trackTimeMillis")
    var Duration: Long?,
    @SerializedName("currency")
    var Currency : String?

)