package woowacourse.movie.mapper

import domain.Position
import woowacourse.movie.dto.PositionDto

fun Position.mapToUIModel(): PositionDto {
    return PositionDto.of(this.row, this.col)
}

fun PositionDto.mapToDomain(): Position {
    return Position(this.row.toInt() - 64, col)
}
