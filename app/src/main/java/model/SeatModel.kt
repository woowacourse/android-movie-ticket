package model

import java.io.Serializable

data class SeatModel(
    val row: Int,
    val column: Int,
    val rank: SeatRankModel,
    val isReserved: Boolean,
) : Serializable
