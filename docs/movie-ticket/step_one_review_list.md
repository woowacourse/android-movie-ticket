# 수정 및 기능 목록
- [ ] Intent 할 때 ID 넘기는거 개선 
- [ ] screenMovieId를 interface 같도록 개선
- [ ] Presenter 객체 생성시 id 에 대한 의존성을 줄이기
- [ ] Adapter ViewHolder 패턴 적용하기
- [ ] Adapter context 주입 받는거 막기
- [ ] Adapter submitList 함수 만들기
- [ ] Adapter itemClickListener 만들기
- [x] 프로젝트 패키징 /feature/screen 단위로 리팩토링
- [x] 네이밍 일치하지 않는 것들 고치기 (ex. MainActivity -> ScreeningMovieActivity)
- [x] pre-push hook 만들기 (test, lint)

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

3) ListView 도 뷰를 재사용할 수 있다. RecyclerView와 ListView의 차이점은 무엇일까?