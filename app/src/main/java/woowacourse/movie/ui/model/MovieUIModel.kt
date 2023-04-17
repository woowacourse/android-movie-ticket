package woowacourse.movie.ui.model

import android.util.Log
import androidx.annotation.DrawableRes
import woowacourse.movie.R
import woowacourse.movie.domain.datetime.ScreeningPeriod
import woowacourse.movie.domain.model.Movie
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

data class MovieUIModel(
    @DrawableRes var posterImage: Int = R.drawable.harrypotter_poster,
    var title: String,
    var screeningDay: ScreeningPeriod,
    var runningTime: Int,
    var description: String = ""
) : Serializable {

    @Throws(IOException::class)
    private fun writeObject(out: ObjectOutputStream) {
        out.apply {
            writeInt(posterImage)
            writeUTF(title)
            writeObject(screeningDay)
            writeInt(runningTime)
            writeUTF(description)
        }
        Log.d("멧돼지", "직렬화 되고있음")
        // 블로그 작성 자료를 위해서 로그 찍어놨습니다 추후 걷어내겠습니다.
    }

    @Throws(IOException::class, ClassCastException::class)
    private fun readObject(`in`: ObjectInputStream) {
        `in`.run {
            posterImage = readInt()
            title = readUTF()
            screeningDay = readObject() as ScreeningPeriod
            runningTime = readInt()
            description = readUTF()
        }
        Log.d("멧돼지", "역직렬화 되고있음")
        // 블로그 작성 자료를 위해서 로그 찍어놨습니다 추후 걷어내겠습니다.
    }

    companion object {
        fun Movie.movieToMovieUiModel(): MovieUIModel =
            MovieUIModel(
                title = this.title,
                screeningDay = this.screeningDay,
                runningTime = this.runningTime,
                description = this.description
            )
    }
}
