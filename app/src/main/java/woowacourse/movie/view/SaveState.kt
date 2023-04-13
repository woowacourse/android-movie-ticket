package woowacourse.movie.view

import android.os.Bundle

interface SaveState {
    val saveStateKey: String
    fun save(outState: Bundle)
    fun load(savedInstanceState: Bundle?)
}
