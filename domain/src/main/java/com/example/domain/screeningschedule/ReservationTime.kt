package com.example.domain.screeningschedule

import com.example.domain.DayOfWeek

class ReservationTime(private val dayOfWeek: DayOfWeek) {

    fun getIntervalTimes(): List<String> = when (dayOfWeek) {
        DayOfWeek.WEEKDAY -> WEEKDAY_TIMES
        DayOfWeek.WEEKEND -> WEEKEND_TIMES
    }

    companion object {
        private val WEEKDAY_TIMES =
            listOf("10:00", "12:00", "14:00", "16:00", "18:00", "20:00", "22:00")

        private val WEEKEND_TIMES =
            listOf("09:00", "11:00", "13:00", "15:00", "17:00", "19:00", "21:00", "23:00")
    }
}
