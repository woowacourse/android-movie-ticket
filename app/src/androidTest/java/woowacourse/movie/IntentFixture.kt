package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import woowacourse.movie.model.BookingCompleteInfo
import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieBookingInfo
import woowacourse.movie.presentation.view.bookcomplete.BookCompleteActivity
import woowacourse.movie.presentation.view.moviedetail.MovieDetailActivity
import woowacourse.movie.presentation.view.seatpick.SeatPickerActivity
import java.time.LocalDate

object IntentFixture {
    fun <T> getBookCompleteIntent(
        startDate: LocalDate,
        endDate: LocalDate,
        toClass: Class<T>
    ): Intent {
        return Intent(
            ApplicationProvider.getApplicationContext(),
            toClass
        ).putExtra(
            BookCompleteActivity.BOOKING_COMPLETE_INFO_INTENT_KEY,
            BookingCompleteInfo(
                MovieBookingInfo(
                    Movie(
                        R.drawable.harry_potter,
                        "해리포터 - 2편",
                        "2023.04.23",
                        "123분",
                        "해리 포터(다니엘 래드클리프)는 위압적인 버논 이모부(리처드 그리피스 )와 냉담한 이모 페투니아 (피오나 쇼), 욕심 많고 버릇없는 사촌 두들리(해리 멜링 ) 밑에서 갖은 구박을 견디며 계단 밑 벽장에서 생활한다. 11살 생일을 며칠 앞둔 어느 날 해리에게 초록색 잉크로 쓰여진 한 통의 편지가 배달되지만 버논 이모부가 편지를 중간에서 가로챈다. 하지만 결국 해리의 생일을 축하하러 온 거인 혼혈 해그리드(로비 콜트레인)가 해리에게 편지를 전해주었는데, 그 편지는 바로 호그와트 입학 통지서였다. 그리고 거인 해그리드는 해리가 마법사라는 사실도 알려주었다.\n" +
                            "\n" +
                            "해리는 호그와트를 선택한다. 런던의 킹스 크로스 역에 있는 비밀의 9와 3/4 승강장에서 호그와트 급행열차를 탄 해리는 열차 안에서 같은 호그와트 마법학교 입학생인와 론 위즐리 (루퍼트 그린트), 헤르미온느 그레인저(엠마 왓슨)을 만나 친구가 된다. 이들과 함께 호그와트에 입학한 해리는, 놀라운 모험의 세계를 경험하며 갖가지 신기한 마법들을 배워 나간다. 또한 빗자루를 타고 공중을 날아다니며 경기하는 스릴 만점의 퀴디치 게임에서 스타로 탄생하게 된다. 그러던 어느 날 해리는 호그와트 지하실에 '마법사의 돌'이 비밀리에 보관되어 있다는 것을 알게되고, 해리의 부모님을 살해한 볼드모트(레이프 파인즈)가 그 돌을 노린다는 사실도 알게 된다. 해리는 볼드모트로부터 마법사의 돌과 호그와트 마법학교를 지키기 위해 필사의 노력을 해서 호그와트를 지킨다. 하지만 마법사의 돌이 깨져, 니콜라스 플라멜이 죽고 만다. 대신 여분의 약을 남겨뒀다. 그래서 바로 죽지는 않았다.",
                        startDate,
                        endDate,
                    ),
                    "2023-04-23",
                    "11:00",
                    2
                ),
                30000,
                "C1, C2"
            )
        )
    }

    fun <T> getMovieInfoIntent(
        startDate: LocalDate,
        endDate: LocalDate,
        toClass: Class<T>
    ): Intent {
        return Intent(
            ApplicationProvider.getApplicationContext(),
            toClass
        ).putExtra(
            SeatPickerActivity.MOVIE_BOOKING_INFO_SCHEDULE_INTENT_KEY,
            MovieBookingInfo(
                Movie(
                    R.drawable.harry_potter,
                    "해리포터 - 2편",
                    "2023.04.23",
                    "123분",
                    "해리 포터(다니엘 래드클리프)는 위압적인 버논 이모부(리처드 그리피스 )와 냉담한 이모 페투니아 (피오나 쇼), 욕심 많고 버릇없는 사촌 두들리(해리 멜링 ) 밑에서 갖은 구박을 견디며 계단 밑 벽장에서 생활한다. 11살 생일을 며칠 앞둔 어느 날 해리에게 초록색 잉크로 쓰여진 한 통의 편지가 배달되지만 버논 이모부가 편지를 중간에서 가로챈다. 하지만 결국 해리의 생일을 축하하러 온 거인 혼혈 해그리드(로비 콜트레인)가 해리에게 편지를 전해주었는데, 그 편지는 바로 호그와트 입학 통지서였다. 그리고 거인 해그리드는 해리가 마법사라는 사실도 알려주었다.\n" +
                        "\n" +
                        "해리는 호그와트를 선택한다. 런던의 킹스 크로스 역에 있는 비밀의 9와 3/4 승강장에서 호그와트 급행열차를 탄 해리는 열차 안에서 같은 호그와트 마법학교 입학생인와 론 위즐리 (루퍼트 그린트), 헤르미온느 그레인저(엠마 왓슨)을 만나 친구가 된다. 이들과 함께 호그와트에 입학한 해리는, 놀라운 모험의 세계를 경험하며 갖가지 신기한 마법들을 배워 나간다. 또한 빗자루를 타고 공중을 날아다니며 경기하는 스릴 만점의 퀴디치 게임에서 스타로 탄생하게 된다. 그러던 어느 날 해리는 호그와트 지하실에 '마법사의 돌'이 비밀리에 보관되어 있다는 것을 알게되고, 해리의 부모님을 살해한 볼드모트(레이프 파인즈)가 그 돌을 노린다는 사실도 알게 된다. 해리는 볼드모트로부터 마법사의 돌과 호그와트 마법학교를 지키기 위해 필사의 노력을 해서 호그와트를 지킨다. 하지만 마법사의 돌이 깨져, 니콜라스 플라멜이 죽고 만다. 대신 여분의 약을 남겨뒀다. 그래서 바로 죽지는 않았다.",
                    startDate,
                    endDate,
                ),
                "2023-04-23",
                "11:00",
                2
            )
        )
    }

    fun <T> getMovieDataIntent(
        startDate: LocalDate,
        endDate: LocalDate,
        toClass: Class<T>
    ): Intent {
        return Intent(
            ApplicationProvider.getApplicationContext(),
            toClass
        ).putExtra(
            MovieDetailActivity.MOVIE_DATA_INTENT_KEY,
            Movie(
                R.drawable.harry_potter,
                "해리포터 - 2편",
                "2023.04.23",
                "123분",
                "해리 포터(다니엘 래드클리프)는 위압적인 버논 이모부(리처드 그리피스 )와 냉담한 이모 페투니아 (피오나 쇼), 욕심 많고 버릇없는 사촌 두들리(해리 멜링 ) 밑에서 갖은 구박을 견디며 계단 밑 벽장에서 생활한다. 11살 생일을 며칠 앞둔 어느 날 해리에게 초록색 잉크로 쓰여진 한 통의 편지가 배달되지만 버논 이모부가 편지를 중간에서 가로챈다. 하지만 결국 해리의 생일을 축하하러 온 거인 혼혈 해그리드(로비 콜트레인)가 해리에게 편지를 전해주었는데, 그 편지는 바로 호그와트 입학 통지서였다. 그리고 거인 해그리드는 해리가 마법사라는 사실도 알려주었다.\n" +
                    "\n" +
                    "해리는 호그와트를 선택한다. 런던의 킹스 크로스 역에 있는 비밀의 9와 3/4 승강장에서 호그와트 급행열차를 탄 해리는 열차 안에서 같은 호그와트 마법학교 입학생인와 론 위즐리 (루퍼트 그린트), 헤르미온느 그레인저(엠마 왓슨)을 만나 친구가 된다. 이들과 함께 호그와트에 입학한 해리는, 놀라운 모험의 세계를 경험하며 갖가지 신기한 마법들을 배워 나간다. 또한 빗자루를 타고 공중을 날아다니며 경기하는 스릴 만점의 퀴디치 게임에서 스타로 탄생하게 된다. 그러던 어느 날 해리는 호그와트 지하실에 '마법사의 돌'이 비밀리에 보관되어 있다는 것을 알게되고, 해리의 부모님을 살해한 볼드모트(레이프 파인즈)가 그 돌을 노린다는 사실도 알게 된다. 해리는 볼드모트로부터 마법사의 돌과 호그와트 마법학교를 지키기 위해 필사의 노력을 해서 호그와트를 지킨다. 하지만 마법사의 돌이 깨져, 니콜라스 플라멜이 죽고 만다. 대신 여분의 약을 남겨뒀다. 그래서 바로 죽지는 않았다.",
                startDate,
                endDate,
            )
        )
    }
}
