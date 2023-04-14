package woowacourse.movie.utils

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.commit(content: FragmentTransaction.() -> Unit) {
    beginTransaction().apply(content).commit()
}
