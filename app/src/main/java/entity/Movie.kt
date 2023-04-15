package entity

import androidx.annotation.DrawableRes
import movie.pricePolicy.PricePolicy
import java.io.Serializable

data class Movie(
    val title: String,
    val runningTime: Int,
    val summary: String,
    val pricePolicy: PricePolicy,

    @DrawableRes
    val poster: Int,
) : Serializable
