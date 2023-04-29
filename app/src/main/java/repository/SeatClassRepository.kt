package repository

import android.graphics.Color
import model.SeatRankModel

object SeatClassRepository {
    const val SRank = "s_rank"
    const val ARank = "a_rank"
    const val BRank = "b_rank"

    val seatRankMap: Map<String, SeatRankModel> = mapOf(
        SRank to SeatRankModel(SRank, Color.parseColor("#19D358"), 22),
        ARank to SeatRankModel(ARank, Color.parseColor("#1B48E9"), 22),
        BRank to SeatRankModel(BRank, Color.parseColor("#8E13EF"), 22),
    )
}
