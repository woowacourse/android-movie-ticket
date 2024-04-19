package woowacourse.movie.model

import woowacourse.movie.R

class MovieRepository {
    private var movies =
        mutableListOf(
            Movie(
                id = 0,
                thumbnail = R.drawable.titanic,
                title = "타이타닉",
                description =
                    "우연한 기회로 티켓을 구해 타이타닉호에 올라탄 자유로운 영혼을 가진 화가 ‘잭’(레오나르도 디카프리오)은 막강한 재력의 약혼자와 함께 1등실에 승선한 ‘로즈’(케이트 윈슬렛)에게 한눈에 반한다. " +
                        "진실한 사랑을 꿈꾸던 ‘로즈’ 또한 생애 처음 황홀한 감정에 휩싸이고, 둘은 운명 같은 사랑에 빠지는데… 가장 차가운 곳에서 피어난 뜨거운 사랑! 영원히 가라앉지 않는 세기의 사랑이 펼쳐진다!",
                date = 1713341410000,
                runningTime = 152,
            ),
        )

    fun getAll(): List<Movie> {
        return movies
    }

    fun getOneById(id: Long): Movie? {
        return movies.firstOrNull { it.id == id }
    }
}
