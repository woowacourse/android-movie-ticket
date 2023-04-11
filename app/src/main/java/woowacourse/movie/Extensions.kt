package woowacourse.movie

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.io.Serializable

inline fun <reified T : Serializable> Fragment.getSerializable(key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arguments?.getSerializable(key, T::class.java)
    } else {
        arguments?.getSerializable(key) as T?
    }

fun FragmentManager.commit(content: FragmentTransaction.() -> Unit) {
    beginTransaction().apply(content).commit()
}

fun Fragment.showBackButton(show: Boolean = true) {
    (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(show)
}
