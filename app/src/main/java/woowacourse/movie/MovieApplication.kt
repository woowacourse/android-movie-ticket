package woowacourse.movie

import android.app.Application
import java.util.TimeZone

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
    }
}