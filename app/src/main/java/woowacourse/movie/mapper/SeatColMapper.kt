package woowacourse.movie.mapper

import domain.SeatCol
import woowacourse.movie.dto.SeatColDto

fun SeatCol.mapToUIModel(): SeatColDto {
    return SeatColDto(this.col)
}

fun SeatColDto.mapToDomain(): SeatCol {
    return SeatCol(this.col)
}
