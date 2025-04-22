package woowacourse.movie

import android.app.Application
import java.util.TimeZone

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 대한민국 시간으로 조정
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
    }
}
