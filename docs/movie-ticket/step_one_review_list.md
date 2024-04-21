# 수정 및 기능 목록
- [x] Intent 할 때 ID 넘기는거 개선 
- [x] screenMovieId를 interface 같도록 개선
- [x] Adapter ViewHolder 패턴 적용하기
- [x] Adapter context 주입 받는거 막기
- [x] ListView tools:listitem 적용
- [x] ListView 의 item Update 기능
- [x] 프로젝트 패키징 /feature/screen 단위로 리팩토링
- [x] 네이밍 일치하지 않는 것들 고치기 (ex. MainActivity -> ScreeningMovieActivity)
- [x] pre-push hook 만들기 (test, lint)
- [ ] 화면 회전 대응하기
# 고민해 볼 것
1) presenter, view 를 기준으로 패키지가 나뉘어져 있는데요
  만약 100개의 presenter가 있으면 각각 100개씩 패키지 하위에 존재하게 되어 그 안에서 찾기 어렵지 않을까요?

2) context 외부 주입의 단점 

```
getView의 parent: ViewGroup과 LayoutInflater 를 활용하면 외부에서 context를 받지 않아도 가능합니다.

외부에서 context를 주입받으면 어떤 단점이 있을지?
LayoutInflater 는 어떤 역할을 하는지?
위 두가지는 안드로이드를 개발하며 중요한 내용들입니다. 한번 고민해보시고 오둥이의 생각을 공유해주세요 👍
```
- ScreeningMovieActivity 가 아니라 MainActivity인가요?

3) FakeMovieRepository DI 하는 방법

만약, 서버와 통신하는 로직이 있다면 FakeMovieRepository 대신 Product 용 Repository를 만들어야 할 것 같습니다.
하지만, 액티비티는 시스템이 생성해주는 것이기에 어떻게 수동으로 DI를 해야할 지 모르겠습니다..