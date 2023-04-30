package model

import java.io.Serializable

data class SeatRankModel(
    val name: String,
    val color: Int,
    val size: Int,
) : Serializable
