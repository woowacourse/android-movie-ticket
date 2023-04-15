package entity

import androidx.annotation.DrawableRes
import java.io.Serializable

data class Movie(
    val title: String,
    val runningTime: Int,
    val summary: String,
    @DrawableRes
    val poster: Int,
) : Serializable
