package woowacourse.movie.theater

object TheaterDatabase {
    val theaters = listOf<TheaterEntity>(
        TheaterEntity(
            id = 0,
            rowSize = 5,
            columnSize = 4,
            sRankRange = listOf(2..3),
            aRankRange = listOf(4..4),
            bRankRange = listOf(0..1),
        ),
        TheaterEntity(
            id = 1,
            rowSize = 7,
            columnSize = 5,
            sRankRange = listOf(3..4),
            aRankRange = listOf((4..6), (2..2)),
            bRankRange = listOf(0..1),
        ),
        TheaterEntity(
            id = 2,
            rowSize = 6,
            columnSize = 5,
            sRankRange = listOf(3..4),
            aRankRange = listOf(5..5),
            bRankRange = listOf(0..2),
        ),
    )
}
