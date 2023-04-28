package model

import androidx.annotation.DrawableRes
import java.io.Serializable

data class MovieModel(
    val title: String,
    val runTime: Int,
    val summary: String,

    @DrawableRes
    val poster: Int,
) : Serializable
