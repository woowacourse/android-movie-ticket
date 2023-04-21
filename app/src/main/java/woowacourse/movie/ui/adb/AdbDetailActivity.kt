package woowacourse.movie.ui.adb

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.AdbState
import woowacourse.movie.ui.BackKeyActionBarActivity
import woowacourse.movie.ui.main.MainActivity.Companion.KEY_ADB
import woowacourse.movie.util.getParcelableExtraCompat
import woowacourse.movie.util.keyError

class AdbDetailActivity : BackKeyActionBarActivity() {
    private val adbImage: ImageView by lazy { findViewById(R.id.adb_detail_img) }
    private val adbDescription: TextView by lazy { findViewById(R.id.adb_description) }
    override fun onCreateView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_adb_detail)

        val adbState: AdbState =
            intent.getParcelableExtraCompat(KEY_ADB) ?: return keyError(KEY_ADB)
        adbImage.setImageResource(adbState.imgId)
        adbDescription.text = adbState.adbDescription
    }
}
