package woowacourse.movie.repository

import woowacourse.movie.domain.screening.Minute
import woowacourse.movie.domain.screening.Movie
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.domain.screening.ScreeningRange
import java.time.LocalDate

object ScreeningRepository {

    private var next_id: Long = 1L
    private val screenings: MutableMap<Long, Screening> = mutableMapOf()

    init {
        initSampleData()
    }

    private fun initSampleData() {
        save(createHarryPorter1Screening())
        save(createHarryPorter2Screening())
        save(createHarryPorter3Screening())
        save(createHarryPorter4Screening())
        save(createHarryPorter5Screening())
        save(createHarryPorter6Screening())
        save(createHarryPorter7Screening())
        save(createHarryPorter8Screening())
    }

    private fun createHarryPorter1Screening(): Screening {
        val screeningRange = ScreeningRange(LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 31))
        val theater = TheaterRepository.findAll().first()
        val movie = Movie(
            "해리 포터와 마법사의 돌",
            Minute(152),
            "오래전 사악한 마법사 볼드모트에게서 부모를 잃지만 그를 몰락시키고 살아남은 아이 해리 포터는 자신이 마법사라는 사실을 알지 못하고 친척인 더즐리 집안에서 자라게 된다. 친척들로부터 갖은 구박을 받ㅈ제임스든 나날을 보내던 도중, 11세가 되던 해에 해리에게로 마법 학교인 호그와트의 입학 통지서가 오게 된다. 이모부인 버넌 더즐리는 해리가 편지를 받지 못하게 방해하지만 해리는 우여곡절 끝에 자신이 마법사라는 사실을 알게 된다. 그리고 그를 맞이하러 온 호그와트의 숲지기 루비우스 해그리드의 안내로 호그와트에 입학하기 위한 준비를 하고, 마침내 학교로 가게 되는데..."
        )
        return Screening(screeningRange, theater, movie)
    }

    private fun createHarryPorter2Screening(): Screening {
        val screeningRange = ScreeningRange(LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 28))
        val theater = TheaterRepository.findAll().first()
        val movie = Movie(
            "해리 포터와 비밀의 방",
            Minute(162),
            "해리 포터는 이모부인 버넌 더즐리의 집에서 최악의 여름방학을 보내고 있었다. 이때 집요정 도비가 나타나 그에게 호그와트 마법학교로 돌아가지 말라고 하나, 해리는 무시한다. 그러자 도비는 더즐리 집의 손님[1]의 머리에 푸딩을 엎지르고 결국 해리는 본인이 마법을 사용했다는 누명을 뒤집어써서 더즐리 가족에게 비밀을 들키고[2]마법부의 편지(미성년 마법사 법률 위반에 대한 경고)를 받고, 2층의 방에 감금되는 수난을 겪는다.\n" +
                    "\n" +
                    "3일 만에 론 위즐리, 조지 위즐리, 프레드 위즐리의 도움으로 빠져나와 버로로 감으로써 자유를 얻었지만 과연 도비의 경고대로 호그와트로 가는 개찰구의 문이 닫히거나 조작된 블러저에게 경기 중 공격을 당해 오른팔이 부러지는 등 심상찮은 일들이 해리에게 일어난다. 학기가 진행될수록, 호그와트 전체에서 \"비밀의 방이 열렸다\"는 괴기스러운 피로 쓴 메시지 아래서 노리스 부인이 공격당한 것을 시작으로 학생들이 차례로 공격당하고 비밀의 방에 대한 괴담이 퍼지는 등 심상찮은 사태들이 연속으로 일어나고 해리에게는 자신만이 들을 수 있는, 사람을 찢어죽이겠다고 위협하는 공포스러운 목소리가 들려온다. 설상가상으로, 해리가 뱀과 말할 수 있는 능력자라는 게 학교에 알려지면서 졸지에 이 일련의 사태의 범인으로 누명을 쓰며 사실상의 왕따를 당하게 된다.\n" +
                    "\n" +
                    "신임 교수인 길더로이 록하트의 해리에 대한 관심과, 울보 머틀의 계속되는 요담, 비밀의 방을 개방한 주범에 대한 문제 등으로 해리는 더욱 궁지에 빠지고 이 과정에서 아거스 필치와 어니 맥밀런, 저스틴 핀치 플레츨리과의 공방을 겪게 된다. 노리스 부인 이후 콜린 크리비, 저스틴, 목이 달랑달랑한 닉과 페네로프 클리어워터가 연속적으로 습격당한데 이어, 자신의 친구인 헤르미온느 그레인저까지 습격당하자 호그와트는 더욱 긴장에 빠진다.\n" +
                    "\n" +
                    "설상가상으로 지니 위즐리까지 슬리데린의 후계자에게 납치당했다는 소식이 들리자 호그와트 폐쇄론까지 제기된다. 이에 해리는 비밀의 방의 진상을 조사하기 위해 지니 위즐리가 가지고 있던 일기장을 꺼내 '비밀의 방에 대하여 알고 있나요?'라고 쓰고, 일기장에 든 톰 마볼로 리들의 영혼과 대화를 하게 된다. 그 일기장에서의 장면을 본 해리는 50년전에 톰 마볼로 리들이 루비우스 해그리드를 잡아내는 장면을 보고는 해그리드가 비밀의 방을 열었다고 생각하지만 그것이 아니라는 것은 곧 밝혀진다. 리들이 해그리드를 모함함으로써 그를 퇴학시켜 아즈카반에 보내도록 한 것이다. 사태를 알아차린 이들은 슬리데린의 후계자를 추적한 끝에 그 후계자가 볼드모트라는 것을 알아내고, 해리는 론과 함께 론의 여동생 지니를 구출하기 위해 비밀의 방에 가는데..."
        )
        return Screening(screeningRange, theater, movie)
    }

    private fun createHarryPorter3Screening(): Screening {
        val screeningRange = ScreeningRange(LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 31))
        val theater = TheaterRepository.findAll().first()
        val movie = Movie(
            "해리 포터와 아즈카반의 죄수",
            Minute(141),
            "13세가 된 해리 포터는 또 한번의 여름 방학을 이모 가족인 더즐리 일가와 우울하게 보내야 했다. 물론 마법을 쓰는 건 일체 금지. 하지만, 버넌 이모부의 누나인 마지 아줌마가 더즐리 가를 방문하면서 상황은 변한다. 위압적인 마지는 해리에겐 늘 공포의 대상. 마지 아줌마의 모욕 때문에 스트레스를 받던 해리는 급기야 '실수로' 그녀를 거대한 괴물 풍선으로 만들어 하늘 높이 띄워 보내버리고 만다. 이모와 이모부에게 벌을 받을 것도 두렵고, 머글 세상에선 마법 사용이 금지되어 있는 것을 어겼기 때문에 호그와트 마법학교와 마법 정부의 징계가 걱정된 해리는 밤의 어둠 속으로 도망치지만, 순식간에 나이트 버스에 태워져 한 술집으로 인도되어 간다. 그 술집의 이름은 '구멍난 냄비'란 뜻의 리키 콜드런. 그곳엔 마법 정부 총리인 코닐리어스 퍼지가 기다리고 있었다. 총리는 해리를 벌주는 대신, 호그와트 학교로 돌아가기 전에 주점에서 하룻밤을 보낼 것을 강권한다. 아즈카반의 감옥을 탈출한 시리우스 블랙이라는 위험한 마법사가 해리를 찾고 있다는 것. 해리가 들은 사실에 의하면 시리우스 블랙은 어둠의 마왕인 볼드모트 경을 해리의 부모가 있는 곳으로 이끌어 결국 부모님 살해를 조종한 배후. 그렇다면 해리 역시 시리우스 블랙의 표적이 될 가능성도 있다는 얘기. 설상가상으로 호그와트 마법학교엔 불청객들이 머물게 된다. 디멘터는 아즈카반의 무시무시한 간수들을 일컫는 말. 블랙으로부터 학생들을 보호한다는 명분으로 호그와트에 머물게 된 그들은 상대의 영혼을 빨아들이는 힘을 갖고 있었다. 불행히도, 그들의 그런 능력은 다른 학생들보다 해리에게 더 큰 영향력을 발휘하고 그들의 존재는 아직 어린 해리를 공포에 몰아넣어 무기력하게 만든다. 하지만 새로 부임한 어둠의 마법 방어법 교수 리머스 루핀이 해리에게 디멘터들의 마법을 막아낼 수 있는 패트로누스 마법을 가르쳐주면서 상황은 반전되는데..."
        )
        return Screening(screeningRange, theater, movie)
    }

    private fun createHarryPorter4Screening(): Screening {
        val screeningRange = ScreeningRange(LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 30))
        val theater = TheaterRepository.findAll().first()
        val movie = Movie(
            "해리 포터와 불의 잔",
            Minute(157),
            "호그와트 마법학교 4학년이 된 해리 포터는 여름방학 동안 위즐리 가족과 함께 퀴디치 월드컵을 관람한다.\n" +
                    "\n" +
                    "경기가 끝난 후 축제 분위기로 들뜬 텐트촌에, 갑자기 볼드모트를 숭배하는 죽음을 먹는 자들이 나타나 테러가 벌어진다. 주위를 온통 광란의 도가니로 몰아넣음과 동시에, 하늘에는 볼드모트의 상징인 '어둠의 표식'이 나타난다.\n" +
                    "\n" +
                    "개학이 되어 다시 호그와트로 돌아간 해리 포터는 알버스 덤블도어 교장으로부터 \"올해에는 퀴디치 게임 대신 트라이위저드 시합을 개최하게 되었다\"는 뜻밖의 소식을 듣는다. 보바통과 덤스트랭 그리고 호그와트의 챔피언들이 1명씩 참가하는 트라이위저드 시합의 우승자는 1천 갈레온의 상금과 최고의 영예를 얻게 된다. 그러나 17세 이상의 학생만이 이 시합에 참가할 수 있었기 때문에, 해리는 자신의 이름을 불의 잔에 넣을 수 없다.\n" +
                    "\n" +
                    "해리는 어둠의 마법 방어법을 가르치기 위해 새로 부임한 매드아이 무디 교수와 돈독한 우의를 다지게 된다. 전직 오러 출신이었던 무디 교수는 어둠의 마법사들을 몹시 증오했으며, 해리에게 커다란 애정을 품고 있었다.\n" +
                    "\n" +
                    "불의 잔은 호그와트의 챔피언으로 세드릭 디고리, 덤스트랭의 챔피언으로 빅터 크룸, 보바통의 챔피언으로 플뢰르 델라쿠르를 선발한다. 그런데 불의 잔은 4번째 챔피언으로 해리 포터를 지명했다. 해리가 불의 잔에 자신이 이름을 몰래 집어넣었다고 모든 사람들이 의심하지만, 뽑힌 챔피언들에겐 마법으로 묶여서 참가해야 할 의무가 자동으로 부여되기에 덤블도어는 트라이위저드 시합을 예정대로 진행시키기로 결정하는데..."
        )
        return Screening(screeningRange, theater, movie)
    }

    private fun createHarryPorter5Screening(): Screening {
        val screeningRange = ScreeningRange(LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7, 31))
        val theater = TheaterRepository.findAll().first()
        val movie = Movie(
            "해리 포터와 불사조 기사단",
            Minute(157),
            "해리 포터는 호그와트 마법학교가 방학에 들어갈 때마다 어쩔 수 없이 이모의 집인 프리빗가 4번지에 머물러야 한다. 사사건건 해리를 괴롭히는 이모 피튜니아 더즐리, 이모부 버넌 더즐리, 이종사촌 더들리 더즐리를 마법을 쓴다는 협박으로 실컷 놀려먹던 해리는, 갑자기 뜻밖의 상황에 휘말리게 된다. 아즈카반 감옥의 간수들인 디멘터들이 프리빗가 4번지에 나타나 해리에게 덤벼든 것이다. 해리는 더들리와 자신을 보호하기 위해 마법을 사용하고, 미성년임에도 머글 앞에서 마법을 사용한 혐의로 청문회에 출두해야 하는 위기를 맞는다.\n" +
                    "\n" +
                    "해리는 우여곡절 끝에 친구 론 위즐리와 헤르미온느 그레인저가 있는 런던 그리몰드가 12번지로 간다. 그 집은 바로 불사조 기사단의 비밀본부며, 대부인 시리우스 블랙의 집이다. 불사조 기사단은 다시 출현한 어둠의 마법사 볼드모트에 대항하기 위해 알버스 덤블도어의 편에 선 마법사들이 비밀리에 조직한 단체다. 해리는 자신도 불사조 기사단에 가입하고 싶다고 떼를 쓰지만, 미성년자라는 이유로 거절당한다.\n" +
                    "\n" +
                    "마침내 지루했던 방학이 끝나고 해리는 호그와트에 복귀한다. 그러나 학교 분위기는 심상치 않다. 새로 부임한 덜로리스 엄브리지 교수는 알버스 덤블도어 교장의 반대편인 코닐리어스 퍼지 마법 정부 총리의 심복이다. 게다가 예언자 일보가 퍼뜨린 악의적인 선전으로, 해리를 보는 학생들의 시선은 곱지 않다. 살벌한 학교 분위기 속에서도 해리는 론, 헤르미온느와 더불어 덤블도어의 군대(Dumbldore's Army)라는 어둠의 마법 방어법 모임을 조직, 몰래 마법을 익힌다. 그런데 해리는 밤마다 볼드모트가 나오는 악몽에 시달리는데…"
        )
        return Screening(screeningRange, theater, movie)
    }

    private fun createHarryPorter6Screening(): Screening {
        val screeningRange = ScreeningRange(LocalDate.of(2024, 8, 1), LocalDate.of(2024, 8, 31))
        val theater = TheaterRepository.findAll().first()
        val movie = Movie(
            "해리 포터와 혼혈 왕자",
            Minute(157),
            "마법부의 미스테리 부서에서 발생한 죽음을 먹는 자들과 불사조 기사단 간의 혈투 후, 시리우스 블랙을 잃은 충격과 슬픔에 잠겨 있는 불사조 기사단과는 반대로 볼드모트와 죽음을 먹는 자들은 머글 세계의 영국 런던에서 죽음을 먹는 자들이 활개를 치며 질서를 어지럽힐 정도로 상기된 분위기를 만끽한다. 더불어 드레이코 말포이를 호그와트 내의 첩자로 삼음과 동시에 세베루스 스네이프로부터 말포이의 조력자로 일한다는 맹세를 받아 낸다.\n" +
                    "\n" +
                    "알버스 덤블도어는 한 지하철 역에서 쉬고(영화판으로, 원작에서는 프리빗가 4번지.) 있던 해리 포터를 버들리 배버튼 (Budleigh Babberton) 마을로 데리고 가서 죽음을 먹는 자들로부터 몸을 숨기고 있던 호러스 슬러그혼을 만나 호그와트의 교수로 복직해달라는 제안을 한 뒤, 새 학기 전까지 해리가 안전하게 머물 수 있도록 위즐리 가족의 집으로 데려다주고 사라진다. 위즐리 집에 미리 와 있던 헤르미온느 그레인저와 론 위즐리, 해리 포터, 세 명은 함께 다이애건 앨리 (Diagon Alley)에 놀러갔다가 말포이 가족과 벨라트릭스 레스트레인지, 그리고 펜리르 그레이백이 보진 & 버크 가게 (Borgin & Burkes)에서 어떤 비밀 의식을 치루는 모습을 우연히 목격하고 이들의 행적에 의심을 품는다. 새 학년 새 학기를 맞이하여 호그와트로 향하는 호그와트 익스프레스 안에서 해리는 투명 망토를 사용하여 드레이코를 염탐해 보기도 하지만 그만 들켜 버려 드레이코에게 코가 부러지는 부상을 당하기도 한다.\n" +
                    "\n" +
                    "새 학기 첫날 6학년생들이 수업 신청을 하고 있을 때, 해리는 미네르바 맥고나걸 교수에게 마법약 수업을 신청하라는 권고를 받고 N.E.W.T 수준 마법약 수업에 출석하게 된다. 스네이프 대신 이번 학기부터 마법약 수업을 다시 맡은 슬러그혼 교수는 해리와 론을 반기며 미처 교재를 챙겨 오지 못한 둘에게 캐비넷 안에 있는 여분의 교재를 쓰도록 배려하는데, 해리는 허름한 그 교재 안에 \"혼혈 왕자(Half-Blood Prince)\"라는 가명의 학생이 나름대로 열심히 공부하며 기록한 갖가지 메모들을 보고 흥미를 갖는다. 그 메모들은 심지어 교재 내의 잘못된 설명들을 보정하고, 개인적으로 연구한 여러 노하우들을 포함하였다. 슬러그혼 교수는 첫 수업 시간부터 수강생들에게 과제를 내는데, \"살아있는 죽음의 약(Draught of Living Death)\"을 가장 잘 만든 학생에게 상품으로 행운의 묘약 \"펠릭스 펠리시스(Felix Felicis)\"를 주기로 한다. 모든 학생들이 그 약을 만드는 데에 고생하는 것과 달리 해리는 교재 그대로 약을 제조하지 않고 대신 혼혈 왕자가 메모한 내용대로 제조한 덕분에 아주 수월하게 과제를 마치게 되고 슬러그혼 교수로부터 펠릭스 펠리시스를 받는다.\n" +
                    "\n" +
                    "그러던 어느 날 교장실로 해리를 부른 덤블도어 교장은 자기와 개인수업을 하자는데..."
        )
        return Screening(screeningRange, theater, movie)
    }

    private fun createHarryPorter7Screening(): Screening {
        val screeningRange = ScreeningRange(LocalDate.of(2024, 9, 1), LocalDate.of(2024, 9, 30))
        val theater = TheaterRepository.findAll().first()
        val movie = Movie(
            "해리 포터와 죽음의 성물 - 1부",
            Minute(157),
            "덤블도어 교장의 죽음 이후, 마법부는 죽음을 먹는 자들에게 점령당하고 호그와트는 위기에 빠진다. 이에 해리와 론, 헤르미온느는 볼드모트를 물리칠 수 있는 유일한 단서이자 그의 영혼이 담긴 ‘성물’ 호크룩스를 찾기 위한 위험한 여정에 나선다. 그러나 영혼이 연결되어 있는 볼드모트와 해리. 볼드모트를 파괴하면 해리의 목숨 또한 위태로워질지 모른다! 죽느냐 죽이느냐, 이제 그 마지막 대결은 극한을 향해 치닫는데…"
        )
        return Screening(screeningRange, theater, movie)
    }

    private fun createHarryPorter8Screening(): Screening {
        val screeningRange = ScreeningRange(LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 31))
        val theater = TheaterRepository.findAll().first()
        val movie = Movie(
            "해리 포터와 죽음의 성물 - 2부",
            Minute(157),
            "이제 모든 것이 끝난다\n" +
                    "마지막 전투에 동참하라!\n" +
                    "\n" +
                    "덤블도어 교장이 남긴 ‘죽음의 성물’의 단서를 쫓던 해리 포터는 볼드모트가 그토록 찾아 다닌 절대적인 힘을 가진 지팡이의 비밀을 통해 드디어 마지막 퍼즐을 완성한다. 이를 풀기 위해 호그와트 마법학교로 돌아온 해리와 친구들은 새로 교장이 된 스네이프 교수와 맞닥뜨린다.\n" +
                    "\n" +
                    "한편, 자신의 영혼이 담긴 호크룩스들이 파괴되었음을 느낀 볼드모트의 등장으로 해리를 주축으로 한 불사조 기사단과 죽음을 먹는 자들 간의 마법전투가 벌어지고 호그와트는 거대한 전쟁터로 변한다.\n" +
                    "전쟁의 틈에서 해리는 덤블도어를 죽인 스네이프의 엄청난 비밀과 함께 볼드모트를 죽일 마지막 호크룩스에 대한 단서를 알게 되는데...\n" +
                    "\n" +
                    "굿바이, 해리 포터!\n" +
                    "판타지의 위대한 역사가 마침내 끝을 맺는다"
        )
        return Screening(screeningRange, theater, movie)
    }

    fun save(screening: Screening) {
        if (screening.id == null) screening.id = next_id++
        screenings[screening.id!!] = screening
    }

    fun findById(id: Long): Screening? {
        return screenings[id]
    }

    fun findAll(): List<Screening> {
        return screenings.values.toList()
    }
}
