package woowacourse.movie.ui.model.seat

class SeatsModel {
    private val ranks = listOf(RankModel.B, RankModel.B, RankModel.S, RankModel.S, RankModel.A)
    private val seats =
        RowModel.createRows(5).flatMapIndexed { rowIndex, row -> ColumnModel.createColumns(4).map { column -> SeatModel(row, column, ranks[rowIndex]) } }

    fun getAll(): Set<SeatModel> = seats.toSet()
}
