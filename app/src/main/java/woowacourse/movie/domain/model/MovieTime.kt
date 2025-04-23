package woowacourse.movie.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalTime

@JvmInline
@Parcelize
value class MovieTime(
    val value: LocalTime = LocalTime.now(),
) : Parcelable {
    companion object {
        /**
         * 문자열을 기반으로 `MovieTime` 객체를 생성합니다.
         *
         * @param time "hh:mm" 형식의 문자열 (예: "14:30")
         * @return 해당 시간 정보를 가지는 `MovieTime` 객체
         */
        fun from(time: String): MovieTime {
            val (hour, minute) = time.split(":").map { it.toInt() }
            return MovieTime(LocalTime.of(hour, minute))
        }

        fun getMovieTimes(dateType: DateType): List<MovieTime> =
            when (dateType) {
                DateType.WEEKDAY -> weekdaysMovieTimes
                DateType.WEEKEND -> weekendsMovieTimes
            }

        private val weekdaysMovieTimes: List<MovieTime> =
            listOf(
                "10:00",
                "12:00",
                "14:00",
                "16:00",
                "18:00",
                "20:00",
                "22:00",
                "00:00",
            ).map { from(it) }

        private val weekendsMovieTimes: List<MovieTime> =
            listOf(
                "09:00",
                "11:00",
                "13:00",
                "15:00",
                "17:00",
                "19:00",
                "21:00",
                "23:00",
            ).map { from(it) }
    }
}
