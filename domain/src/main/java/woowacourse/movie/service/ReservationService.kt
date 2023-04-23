package woowacourse.movie.service

import woowacourse.movie.dto.ReservationResultDto
import woowacourse.movie.repository.MovieRepository
import woowacourse.movie.repository.ReservationResultRepository

object ReservationService {

    private const val NOT_EXIST_RESERVATION_RESULT_ERROR = "아이디가 %d인 예매 결과가 존재하지 않습니다."

    fun findReservationResultById(id: Long): ReservationResultDto {
        val reservationResult = ReservationResultRepository.findById(id)
            ?: throw IllegalArgumentException(NOT_EXIST_RESERVATION_RESULT_ERROR.format(id))

        val movie = MovieRepository.findById(reservationResult.movieId)

        return ReservationResultDto.of(movie, reservationResult)
    }
}
