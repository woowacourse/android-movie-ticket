package woowacourse.movie

import android.content.Context
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Screen
import java.text.NumberFormat
import java.util.Locale

fun ViewInteraction.repeatClick(count: Int) {
    repeat(count) {
        perform(click())
    }
}

fun getDummyScreen(): Screen {
    return Screen(
        id = 1,
        Movie(
            "해리 포터와 마법사의 돌",
            152,
            R.drawable.img_poster,
            "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                "해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
        ),
        "2024-03-01",
        13_000,
    )
}

fun Reservation.currency(context: Context): String {
    val amount =
        when (Locale.getDefault().country) {
            Locale.KOREA.country -> context.getString(R.string.price_format_kor, totalPrice)
            else -> NumberFormat.getCurrencyInstance(Locale.getDefault()).format(totalPrice)
        }

    return context.getString(R.string.reserve_amount, amount)
}
