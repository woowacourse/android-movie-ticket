package woowacourse.movie.mapper

import com.example.domain.model.PlayingTimes
import woowacourse.movie.formatter.DateFormatter
import woowacourse.movie.model.PlayingTimesModel

fun PlayingTimesModel.toPlayingTimes(): PlayingTimes {
    val startLocalDate = DateFormatter.formatToOriginal(startDate)
    val endLocalDate = DateFormatter.formatToOriginal(endDate)
    return PlayingTimes(startLocalDate, endLocalDate)
}

fun PlayingTimes.toPlayingTimesModel() =
    PlayingTimesModel(DateFormatter.formatToString(startDate), DateFormatter.formatToString(endDate))
