package woowacourse.movie.moviereservation.uimodel

import android.util.Log

class ScreeningDateTimesUiModel(val dateTimes:List<ScreeningDateTimeUiModel>) {


    fun screeningTimeOfDate(position:Int):List<String> =
        dateTimes[position].times

    fun dates():List<String> = dateTimes.map { it.date }

    fun defaultTimes(): List<String> {
        Log.d("테스트", "${dateTimes.first().times}")
        return dateTimes.first().times
    }

}
