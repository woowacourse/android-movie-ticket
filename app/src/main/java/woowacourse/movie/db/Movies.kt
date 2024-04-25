package woowacourse.movie.db

import woowacourse.movie.R
import woowacourse.movie.model.Movie
import java.time.LocalDate

object Movies {
    private val movies: List<Movie> =
        listOf(
            Movie(
                0,
                R.drawable.img_sorcerers_stone,
                "해리 포터와 마법사의 돌",
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 31),
                "152분",
                """
                해리 포터(다니엘 래드클리프 분)는 위압적인 버논 숙부(리챠드 그리피스 분)와 냉담한 이모 페투니아 
                (피오나 쇼 분), 욕심 많고 버릇없는 사촌 더즐리(해리 멜링 분) 밑에서 갖은 구박을 견디며 계단 밑 
                벽장에서 생활한다. 이모네 식구들 역시 해리와의 동거가 불편하기는 마찬가지. 이모 페투니아에겐 해리가 
                이상한(?) 언니 부부에 관한 기억을 떠올리게 만드는 달갑지 않은 존재다. 11살 생일이 며칠 앞으로 
                다가왔지만 한번도 생일파티를 치르거나 제대로 된 생일선물을 받아 본 적이 없는 해리로서는 특별히 신날 
                것도 기대 할 것도 없다. 11살 생일을 며칠 앞둔 어느 날 해리에게 초록색 잉크로 쓰여진 한 통의 편지가 
                배달된다. 그 편지의 내용은 다름 아닌 해리의 11살 생일을 맞이하여 전설적인 “호그와트 마법학교”에서 
                보낸 입학초대장이었다. 그리고 해리의 생일을 축하하러 온 거인 해그리드는 해리가 모르고 있었던 해리의 
                진정한 정체를 알려주는데. 그것은 바로 해리가 굉장한 능력을 지닌 마법사라는 것! 해리는 해그리드의 
                지시대로 자신을 구박하던 이모네 집을 주저없이 떠나 호그와트행을 택한다. 런던의 킹스크로스 역에 있는 
                비밀의 9와 3/4 승장장에서 호그와트 특급열차를 탄 해리는 열차 안에서 같은 호그와트 마법학교 입학생인 
                헤르미온느 그레인저(엠마 왓슨 분)와 론 위즐리 (루퍼트 그린트 분)를 만나 친구가 된다. 이들과 함께 
                호그와트에 입학한 해리는, 놀라운 모험의 세계를 경험하며 갖가지 신기한 마법들을 배워 나간다. 또한 
                빗자루를 타고 공중을 날아다니며 경기하는 스릴 만점의 퀴디치 게임에서 스타로 탄생하게 되며, 용·머리가 
                셋 달린 개·유니콘·켄타우루스·히포그리프(말 몸에 독수리 머리와 날개를 가진 괴물)등 신비한 동물들과 
                마주치며 모험을 즐긴다. 그러던 어느 날 해리는 호그와트 지하실에 영원한 생을 가져다주는 마법사의 
                돌이 비밀리에 보관되어 있다는 것을 알게 되고, 해리의 부모님을 죽인 볼드모트가 그 돌을 노린다는 사실도 
                알게 된다. 볼드모트는 바로 해리를 죽이려다 실패하고 이마에 번개모양의 흉터를 남긴 장본인이다. 해리는 
                볼드모트로부터 마법의 돌과 호그와트 마법학교를 지키기 위해 필사의 노력을 하는데...
                """.trimIndent(),
            ),
            Movie(
                1,
                R.drawable.img_secret_room,
                "해리 포터와 비밀의 방",
                LocalDate.of(2024, 2, 1),
                LocalDate.of(2024, 2, 29),
                "160분",
                """
                해리 포터에겐 이번 여름방학이 별로 즐겁질 못했다. 마법이라면 질색을 하는 페투니아 이모(피오나 쇼 분)와 
                버논 이모부(리차드 그리피스 분)의 구박도 그렇지만, 무엇보다 속상한 건 단짝이었던 론 위즐리(루퍼트 그린트 분)와 
                헤르미온느 그레인저(엠마 왓슨 분)가 그 사이 자신을 까맣게 잊었는지 자신의 편지에 답장 한 통 없다는 것.
                그러던 어느날 꼬마 집요정 도비가 해리의 침실에 나타나 뜻밖의 얘기를 한다. 호그와트 마법학교로 돌아가면 
                무서운 일을 당할 거라는 것. 도비는 해리를 학교에 못 가게 하려고 자신이 여태까지 론과 헤르미온느의 답장을 
                가로채 왔음을 고백한다. 그러나 도비와 더즐리 가족의 방해에도 불구, 해리는 론과 그의 형제들이 타고 온 하늘을 
                나는 자동차를 타고 이모집을 탈출, 따뜻한 가족애가 넘치는 론 위즐리의 집으로 간다.
                개학을 앞두고 학교에 가는 날, 론과 해리는 뭔가의 방해로 9와 3/4 승강장에 못 들어가는 바람에 개학식에 
                지각할 위기에 처한다. 결국 하늘을 나는 자동차 포드 앵글리아를 타고 천신만고끝에 학교에 도착했으나 공교롭게도 
                차가 학교 선생님들이 소중히 여기는 '커다란 버드나무' 위에 불시착하는 바람에 화가 난 스네이프 교수로부터 
                퇴학 경고를 받게 된다. 한편 1학년 때 해리가 보여준 영웅적인 활약상은 학교 전체에 소문이 나고, 그 덕에 해리는 
                원치 않는 관심의 초점이 된다. 론의 여동생 지니, 사진작가 지망생 콜린 크리비 등의 신입생과 어둠의 마법 방어술을 
                가르치는 신임 교수 질데로이 록허트가 새롭게 해리포터의 팬이 된다.
                남의 시선 끌기를 좋아하는 잘난척하는 성격 탓에 주변에서 따돌림 당하는 록허트 교수는 해리와 친해지고 싶어 
                안달하지만, 그 역시 학교에서 일어나는 무서운 사건에 대해 뾰족한 설명을 못해준다. 모든 이목은 해리에게 집중되고, 
                결국 급우들은 해리를 의심하기에 이른다. 물론 론과 헤르미온느, 그리고 수수께끼의 일기장에 마음을 뺏긴 론의 
                동생 지니만은 끝까지 해리를 믿는다.
                자신을 믿는 친구들을 실망시킬 수는 없는 법. 해리는 -도움을 준다며 되려 걸리적 대는 록허트 교수가 다소 방해가 
                되긴 하지만- 어둠의 세력과 맞서 싸울 결심을 하는데..
                """.trimIndent(),
            ),
            Movie(
                2,
                R.drawable.img_prisoner_of_azkaban,
                "해리 포터와 아즈카반의 죄수",
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 3, 31),
                "141분",
                """
                13세가 된 해리 포터(다니엘 래드클리프)는 또 한번의 여름 방학을 이모 가족인 더즐리 일가와 우울하게 보내야 했다. 
                물론 마법을 쓰는 건 일체 금지. 하지만, 버논 이모부의 누이인 마지 아줌마(팸 페리스)가 더즐리 가를 방문하면서 
                상황은 변한다. 위압적인 마지는 해리에겐 늘 공포의 대상. 마지 아줌마 때문에 스트레스를 받던 해리는 급기야 
                '실수로' 그녀를 거대한 괴물 풍선으로 만들어 하늘 높이 띄워 보내버리고 만다.
                이모와 이모부에게 벌을 받을 것도 두렵고, 일반 세상에선 마법 사용이 금지돼 있는 것을 어겼기 때문에 호그와트 
                마법학교와 마법부의 징계가 걱정된 해리는 밤의 어둠 속으로 도망치지만, 순식간에 근사한 보라색 3층 버스에 
                태워져 한 술집으로 인도되어 간다. 그 술집의 이름은 '구멍난 냄비'란 뜻의 리키 콜드런. 그곳엔 마법부 장관인 
                코넬리우스 퍼지가 기다리고 있었다. 장관은 해리를 벌주는 대신 호그와트 학교로 돌아가기 전에 주점에서 
                하룻밤을 보낼 것을 강권한다. 아즈카반의 감옥을 탈출한 시리우스 블랙이라는 위험한 마법사가 해리를 찾고 있다는 
                것. 전설에 의하면 시리우스 블랙은 어둠의 마왕인 볼드모트 경을 해리의 부모가 있는 곳으로 이끌어 결국 부모님을 
                죽이도록 만든 당사자. 그렇다면 해리 역시 시리우스 블랙의 표적이 될 가능성도 있다는 얘기. 설상가상으로 호그와트 
                마법학교엔 '디멘터'라는 불청객들이 머물게 된다. 디멘터는 아즈카반의 무시무시한 간수들을 일컫는 말. 블랙으로부터 
                학생들을 보호한다는 명분으로 호그와트에 머물게 된 그들은 상대의 영혼을 빨아들이는 힘을 갖고 있었다. 불행히도, 
                그들의 그런 능력은 다른 학생들보다 해리에게 더 큰 영향력을 발휘하고 그들의 존재는 아직 어린 해리를 공포에 
                몰아넣어 무기력하게 만든다. 하지만 새로 부임한 어둠의 마법 방어술 교수 루핀(데이빗 튤리스)이 해리에게 
                디멘터들의 마법을 막아낼 수 있는 '패트로누스' 마법을 가르쳐주면서 상황은 반전된다.
                한편 호그와트에서의 3학년 수업은 해리에게 짜릿한 체험도 많이 안겨준다. '벅빅' (반은 독수리, 반은 말 모양의 
                일명 '히포크리프'란 생물)과 같은 흥미로운 짐승과의 만남, 사이빌 트릴로니 교수(엠마 톰슨)나 '그림'으로 알려진 
                죽음의 징조와의 섬뜩한 대면 등등. 그외에도 해리에겐 또 다른 문제가 찾아온다. 헤르미온느(엠마 왓슨)가 갑자기 
                사라졌다 나타났다 하는 이상한 돌출 행동을 보이기 시작한 것. 친구 론(루퍼트 그린트)과, 새 학기 들어 '신비한 
                동물 돌보기' 과목 교수로 발령 난 거인 해그리드 (로비 콜트레인)의 도움으로 해리는 그 수수께끼를 풀어나간다. 
                시리우스 블랙과 해리의 불가피한 대결은 점점 다가오고, 루핀 교수와 블랙의 모호한 관계는 해리를 혼란에 빠뜨린다. 
                스네이프 교수(알란 릭만)가 그토록 밝히고자 하는 어두운 비밀은 또 과연 무엇인가? 해리는 자신의 모든 용기와 
                마법의 힘과 친구들의 도움을 총동원, 이러한 의문점들을 풀고 자신과 시리우스 블랙 사이에 얽혀있는 비밀을 파헤쳐 
                가는데...
                """.trimIndent(),
            ),
        )

    fun obtainMovies(): List<Movie> = movies.toList()

    fun obtainMovie(movieId: Int): Movie = movies[movieId]

    fun obtainScreeningDates(movieId: Int): List<LocalDate> {
        val dates = mutableListOf<LocalDate>()
        var currentDate = movies[movieId].firstScreeningDate

        while (!currentDate.isAfter(movies[movieId].lastScreeningDate)) {
            dates.add(currentDate)
            currentDate = currentDate.plusDays(1)
        }

        return dates.toList()
    }
}
