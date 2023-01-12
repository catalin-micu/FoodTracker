package com.example.foodtracker.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeDataClass(
    val calories: Int,
    val carbs: String,
    val fat: String,
    val id: Int,
    @Json(name = "image") val image: String,
    val imageType: String,
    val protein: String,
    val title: String
) : Parcelable {
    val hasProtein
        get() = protein != ""
}