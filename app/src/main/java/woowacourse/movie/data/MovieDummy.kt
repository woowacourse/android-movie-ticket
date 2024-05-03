package woowacourse.movie.data

import woowacourse.movie.R
import woowacourse.movie.model.Movie
import java.time.LocalDate

object MovieDummy {
    fun movies(): List<Movie> {
        val movies: List<Movie> =
            listOf(
                Movie(
                    0,
                    "해리포터와 마법사의 돌",
                    listOf(LocalDate.of(2024, 3, 1), LocalDate.of(2024, 4, 7)),
                    152,
                    "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
                    R.drawable.harry_sorcerer_stone,
                ),
                Movie(
                    1,
                    "해리포터와 비밀의 방",
                    listOf(LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 29)),
                    162,
                    "《해리 포터와 비밀의 방》은 해리 포터는 이모부인 버넌 더즐리의 집에서 최악의 여름방학을 보내고 있었다. 이때 집요정 도비가 나타나 그에게 호그와트 마법학교로 돌아가지 말라고 하나, 해리는 무시한다. 그러자 도비는 더즐리 집의 손님[1]의 머리에 푸딩을 엎지르고 결국 해리는 본인이 마법을 사용했다는 누명을 뒤집어써서 더즐리 가족에게 비밀을 들키고[2]마법부의 편지(미성년 마법사 법률 위반에 대한 경고)를 받고, 2층의 방에 감금되는 수난을 겪는다.",
                    R.drawable.harry_secret_room,
                ),
                Movie(
                    2,
                    "해리포터와 아즈카반의 죄수",
                    listOf(LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 29)),
                    162,
                    "《해리 포터와 비밀의 방》은 해리 포터는 이모부인 버넌 더즐리의 집에서 최악의 여름방학을 보내고 있었다. 이때 집요정 도비가 나타나 그에게 호그와트 마법학교로 돌아가지 말라고 하나, 해리는 무시한다. 그러자 도비는 더즐리 집의 손님[1]의 머리에 푸딩을 엎지르고 결국 해리는 본인이 마법을 사용했다는 누명을 뒤집어써서 더즐리 가족에게 비밀을 들키고[2]마법부의 편지(미성년 마법사 법률 위반에 대한 경고)를 받고, 2층의 방에 감금되는 수난을 겪는다.",
                    R.drawable.harry_prisoner_of_azkaban,
                ),
                Movie(
                    3,
                    "해리포터와 불의 잔",
                    listOf(LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 29)),
                    162,
                    "《해리 포터와 비밀의 방》은 해리 포터는 이모부인 버넌 더즐리의 집에서 최악의 여름방학을 보내고 있었다. 이때 집요정 도비가 나타나 그에게 호그와트 마법학교로 돌아가지 말라고 하나, 해리는 무시한다. 그러자 도비는 더즐리 집의 손님[1]의 머리에 푸딩을 엎지르고 결국 해리는 본인이 마법을 사용했다는 누명을 뒤집어써서 더즐리 가족에게 비밀을 들키고[2]마법부의 편지(미성년 마법사 법률 위반에 대한 경고)를 받고, 2층의 방에 감금되는 수난을 겪는다.",
                    R.drawable.harry_goblet_of_fire,
                ),
            )

        val movieTwo: List<Movie> = List(2500) { movies }.flatten()
        return (0 until 10000).map { movieTwo[it].copy(id = it) }
    }
}
