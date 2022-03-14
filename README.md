# jUnit5을 이용한 Test Code

## TDD(Test-Driven-Development)
- `“테스트 주도 개발 : 테스트가 개발을 이끌어 나간다.”` 로 정의
- `"작은 단위의 테스트 케이스"`를 작성하고 이를 통과하는 코드를 추가하는 단계를 반복하여 구현
- TDD를 왜 사용하는가?
    - 개발 단계 초기에 문제를 발견
    - 추후에 코드를 리팩토링하거나 라이브러리 업그레이드 등에서 기존 기능이 올바르게 작동하는지 확인
    - 기능에 대한 불확실성을 감소
    - 시스템에 대한 실제 문서를 제공 → 즉, 단위 테스트 자체가 문서로 사용할 수 있음

## Controller Test Code 중점
- Controller의 역할에 충실하게 Test 코드 작성 필요
  - Client의 Request를 잘받아서 Server에 넘겨주고 Server의 Response를 Client에게 Return