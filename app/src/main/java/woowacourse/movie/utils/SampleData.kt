package woowacourse.movie.utils

import movie.Cinema
import movie.Movie
import movie.MovieSchedule
import movie.ScreeningDate
import woowacourse.movie.R
import java.time.LocalDate

object SampleData {
    val CINEMA_SAMPLE: Cinema = Cinema(
        listOf(
            MovieSchedule(
                movie = Movie(
                    title = "해리 포터와 마법사의 돌",
                    runningTime = 152,
                    summary = "해리 포터(다니엘 래드클리프)는 위압적인 버논 이모부(리처드 그리피스 )와 냉담한 이모 페투니아 (피오나 쇼), 욕심 많고 버릇없는 사촌 두들리(해리 멜링 ) 밑에서 갖은 구박을 견디며 계단 밑 벽장에서 생활한다. 11살 생일을 며칠 앞둔 어느 날 해리에게 초록색 잉크로 쓰여진 한 통의 편지가 배달되지만 버논 이모부가 편지를 중간에서 가로챈다. 하지만 결국 해리의 생일을 축하하러 온 거인 혼혈 해그리드(로비 콜트레인)가 해리에게 편지를 전해주었는데, 그 편지는 바로 호그와트 입학 통지서였다. 그리고 거인 해그리드는 해리가 마법사라는 사실도 알려주었다.\n" +
                        "\n" +
                        "해리는 호그와트를 선택한다. 런던의 킹스 크로스 역에 있는 비밀의 9와 3/4 승강장에서 호그와트 급행열차를 탄 해리는 열차 안에서 같은 호그와트 마법학교 입학생인와 론 위즐리 (루퍼트 그린트), 헤르미온느 그레인저(엠마 왓슨)을 만나 친구가 된다. 이들과 함께 호그와트에 입학한 해리는, 놀라운 모험의 세계를 경험하며 갖가지 신기한 마법들을 배워 나간다. 또한 빗자루를 타고 공중을 날아다니며 경기하는 스릴 만점의 퀴디치 게임에서 스타로 탄생하게 된다. 그러던 어느 날 해리는 호그와트 지하실에 '마법사의 돌'이 비밀리에 보관되어 있다는 것을 알게되고, 해리의 부모님을 살해한 볼드모트(레이프 파인즈)가 그 돌을 노린다는 사실도 알게 된다. 해리는 볼드모트로부터 마법사의 돌과 호그와트 마법학교를 지키기 위해 필사의 노력을 해서 호그와트를 지킨다. 하지만 마법사의 돌이 깨져, 니콜라스 플라멜이 죽고 만다. 대신 여분의 약을 남겨뒀다. 그래서 바로 죽지는 않았다.",
                    poster = R.drawable.poster_sorcerers_stone,
                ),
                screeningDate = ScreeningDate(
                    startDate = LocalDate.of(2023, 4, 1),
                    endDate = LocalDate.of(2023, 4, 30),
                ),
            ),
            MovieSchedule(
                movie = Movie(
                    title = "해리 포터와 비밀의 방",
                    runningTime = 112,
                    summary = "해리 포터는 이모부인 버넌 더즐리의 집에서 최악의 여름방학을 보내고 있었다. 이때 집요정 도비가 나타나 그에게 호그와트 마법학교로 돌아가지 말라고 하나, 해리는 무시한다. 그러자 도비는 더즐리 집의 손님[1]의 머리에 푸딩을 엎지르고 결국 해리는 본인이 마법을 사용했다는 누명을 뒤집어써서 더즐리 가족에게 비밀을 들키고[2]마법부의 편지(미성년 마법사 법률 위반에 대한 경고)를 받고, 2층의 방에 감금되는 수난을 겪는다.\n" +
                        "\n" +
                        "3일 만에 론 위즐리, 조지 위즐리, 프레드 위즐리의 도움으로 빠져나와 버로로 감으로써 자유를 얻었지만 과연 도비의 경고대로 호그와트로 가는 개찰구의 문이 닫히거나 조작된 블러저에게 경기 중 공격을 당해 오른팔이 부러지는 등 심상찮은 일들이 해리에게 일어난다. 학기가 진행될수록, 호그와트 전체에서 \"비밀의 방이 열렸다\"는 괴기스러운 피로 쓴 메시지 아래서 노리스 부인이 공격당한 것을 시작으로 학생들이 차례로 공격당하고 비밀의 방에 대한 괴담이 퍼지는 등 심상찮은 사태들이 연속으로 일어나고 해리에게는 자신만이 들을 수 있는, 사람을 찢어죽이겠다고 위협하는 공포스러운 목소리가 들려온다. 설상가상으로, 해리가 뱀과 말할 수 있는 능력자라는 게 학교에 알려지면서 졸지에 이 일련의 사태의 범인으로 누명을 쓰며 사실상의 왕따를 당하게 된다.\n" +
                        "\n" +
                        "신임 교수인 길더로이 록하트의 해리에 대한 관심과, 울보 머틀의 계속되는 요담, 비밀의 방을 개방한 주범에 대한 문제 등으로 해리는 더욱 궁지에 빠지고 이 과정에서 아거스 필치와 어니 맥밀런, 저스틴 핀치 플레츨리과의 공방을 겪게 된다. 노리스 부인 이후 콜린 크리비, 저스틴, 목이 달랑달랑한 닉과 페네로프 클리어워터가 연속적으로 습격당한데 이어, 자신의 친구인 헤르미온느 그레인저까지 습격당하자 호그와트는 더욱 긴장에 빠진다.\n" +
                        "\n" +
                        "설상가상으로 지니 위즐리까지 슬리데린의 후계자에게 납치당했다는 소식이 들리자 호그와트 폐쇄론까지 제기된다. 이에 해리는 비밀의 방의 진상을 조사하기 위해 지니 위즐리가 가지고 있던 일기장을 꺼내 '비밀의 방에 대하여 알고 있나요?'라고 쓰고, 일기장에 든 톰 마볼로 리들의 영혼과 대화를 하게 된다. 그 일기장에서의 장면을 본 해리는 50년전에 톰 마볼로 리들이 루비우스 해그리드를 잡아내는 장면을 보고는 해그리드가 비밀의 방을 열었다고 생각하지만 그것이 아니라는 것은 곧 밝혀진다. 리들이 해그리드를 모함함으로써 그를 퇴학시켜 아즈카반에 보내도록 한 것이다. 사태를 알아차린 이들은 슬리데린의 후계자를 추적한 끝에 그 후계자가 볼드모트라는 것을 알아내고, 해리는 론과 함께 론의 여동생 지니를 구출하기 위해 비밀의 방에 가는데...",
                    poster = R.drawable.poster_chamber_of_secrets,
                ),
                screeningDate = ScreeningDate(
                    startDate = LocalDate.of(2023, 3, 1),
                    endDate = LocalDate.of(2023, 4, 30),
                ),
            ),
        ),
    )

    val CINEMA_SAMPLE_TEN: Cinema = Cinema(
        List(1000) {
            MovieSchedule(
                movie = Movie(
                    title = "해리 포터와 비밀의 방 $it",
                    runningTime = 112,
                    summary = "해리 포터는 이모부인 버넌 더즐리의 집에서 최악의 여름방학을 보내고 있었다. 이때 집요정 도비가 나타나 그에게 호그와트 마법학교로 돌아가지 말라고 하나, 해리는 무시한다. 그러자 도비는 더즐리 집의 손님[1]의 머리에 푸딩을 엎지르고 결국 해리는 본인이 마법을 사용했다는 누명을 뒤집어써서 더즐리 가족에게 비밀을 들키고[2]마법부의 편지(미성년 마법사 법률 위반에 대한 경고)를 받고, 2층의 방에 감금되는 수난을 겪는다.\n" +
                        "\n" +
                        "3일 만에 론 위즐리, 조지 위즐리, 프레드 위즐리의 도움으로 빠져나와 버로로 감으로써 자유를 얻었지만 과연 도비의 경고대로 호그와트로 가는 개찰구의 문이 닫히거나 조작된 블러저에게 경기 중 공격을 당해 오른팔이 부러지는 등 심상찮은 일들이 해리에게 일어난다. 학기가 진행될수록, 호그와트 전체에서 \"비밀의 방이 열렸다\"는 괴기스러운 피로 쓴 메시지 아래서 노리스 부인이 공격당한 것을 시작으로 학생들이 차례로 공격당하고 비밀의 방에 대한 괴담이 퍼지는 등 심상찮은 사태들이 연속으로 일어나고 해리에게는 자신만이 들을 수 있는, 사람을 찢어죽이겠다고 위협하는 공포스러운 목소리가 들려온다. 설상가상으로, 해리가 뱀과 말할 수 있는 능력자라는 게 학교에 알려지면서 졸지에 이 일련의 사태의 범인으로 누명을 쓰며 사실상의 왕따를 당하게 된다.\n" +
                        "\n" +
                        "신임 교수인 길더로이 록하트의 해리에 대한 관심과, 울보 머틀의 계속되는 요담, 비밀의 방을 개방한 주범에 대한 문제 등으로 해리는 더욱 궁지에 빠지고 이 과정에서 아거스 필치와 어니 맥밀런, 저스틴 핀치 플레츨리과의 공방을 겪게 된다. 노리스 부인 이후 콜린 크리비, 저스틴, 목이 달랑달랑한 닉과 페네로프 클리어워터가 연속적으로 습격당한데 이어, 자신의 친구인 헤르미온느 그레인저까지 습격당하자 호그와트는 더욱 긴장에 빠진다.\n" +
                        "\n" +
                        "설상가상으로 지니 위즐리까지 슬리데린의 후계자에게 납치당했다는 소식이 들리자 호그와트 폐쇄론까지 제기된다. 이에 해리는 비밀의 방의 진상을 조사하기 위해 지니 위즐리가 가지고 있던 일기장을 꺼내 '비밀의 방에 대하여 알고 있나요?'라고 쓰고, 일기장에 든 톰 마볼로 리들의 영혼과 대화를 하게 된다. 그 일기장에서의 장면을 본 해리는 50년전에 톰 마볼로 리들이 루비우스 해그리드를 잡아내는 장면을 보고는 해그리드가 비밀의 방을 열었다고 생각하지만 그것이 아니라는 것은 곧 밝혀진다. 리들이 해그리드를 모함함으로써 그를 퇴학시켜 아즈카반에 보내도록 한 것이다. 사태를 알아차린 이들은 슬리데린의 후계자를 추적한 끝에 그 후계자가 볼드모트라는 것을 알아내고, 해리는 론과 함께 론의 여동생 지니를 구출하기 위해 비밀의 방에 가는데...",
                    poster = R.drawable.poster_chamber_of_secrets,
                ),
                screeningDate = ScreeningDate(
                    startDate = LocalDate.of(2023, 3, 1),
                    endDate = LocalDate.of(2023, 4, 30),
                ),
            )
        },
    )
}
