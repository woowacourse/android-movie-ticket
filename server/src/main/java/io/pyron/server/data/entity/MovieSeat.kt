package io.pyron.server.data.entity

data class MovieSeat(
    val id: Long,
    val movieSeatBoardId: Long,
    val number: Int,
    val tier: Tier,
)
