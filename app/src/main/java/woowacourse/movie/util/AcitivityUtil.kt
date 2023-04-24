package woowacourse.movie.util

import android.app.Activity
import android.widget.Toast

fun Activity.failedToCreate(errorMessage: String) {
    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()

    return finish()
}
