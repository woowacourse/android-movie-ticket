package woowacourse.movie

import java.time.LocalTime

@JvmInline
value class Time(
    private val value: LocalTime,
) {
    override fun toString(): String = "%02d:%02d".format(value.hour, value.minute)

    companion object {
        val weekdaysTimes: List<Time> =
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

        val weekendsTimes: List<Time> =
            listOf(
                "10:00",
                "12:00",
                "14:00",
                "16:00",
                "18:00",
                "20:00",
                "22:00",
            ).map { from(it) }

        private fun from(value: String): Time {
            val time = value.split(":")
            return Time(
                LocalTime.of(
                    time[0].toInt(),
                    time[1].toInt(),
                ),
            )
        }
    }
}
