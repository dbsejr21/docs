# 9. 날짜 처리 공략하기



- SimpleDateFormat 클래스는 스레드 세이프가 아니다.
  - 날짜 클래스와 문자열을 상호 변환하기 위해 DateFormat 클래스를 이용했다.
  - 여러 곳에서 변환 처리를 할 경우 인스턴스를 재활용하면 될 것 같으나, 이렇게 하면 안좋다.
  - 스레드 세이프 하지 않으므로 의도치 않은 결과 값이 생성 될 수 있다.
- DateTimeFormatter 클래스는 스레드 세이프다.
  - SimpleDateFormat 클래스와 달리 DateTimeFormatter는 스레드 세이프하다.