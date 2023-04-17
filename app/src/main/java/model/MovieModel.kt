package model

import androidx.annotation.DrawableRes
import java.io.Serializable

data class MovieModel(
    val title: String,
    val runningTime: Int,
    val summary: String,

    @DrawableRes
    val poster: Int,
) : Serializable {
    companion object {
        val EMPTY = MovieModel("", 0, "", 0)
    }
}
