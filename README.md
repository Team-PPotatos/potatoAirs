# Potato-Air

## 🐬프로젝트 소개
대한민국 대표 항공사 Potato-Air의 항공권을 예매하도록 하는 프로그램.

<br/>

---

## 🐬시스템 요구사항

```
- 유저는 로그인하기, 티켓 예매하기, 예매 취소하기, 예매 내역 조회하기, 비밀번호 변경하기, 프로그램 종료, 
총 6가지 예매 메뉴를 이용할 수 있다.

- 비로그인 후 티켓을 예매하려할 시, 회원/비회원 여부를 판단하여 그에 따라 로그인 또는 회원가입을 유도하도록 한다.

- 티켓 예매 시 항공권 정보가 띄워지며(출발지, 도착지, 시각) 항공예약자 정보(이름), 예매를 원하는 항공편(index), 
좌석(index)을 입력받아 예매할 수 있도록 한다.

- 클래스는 메뉴 선택을 다룰 Application, Application에서 호출되는 Airline, 그리고 Service 세가지로 구성된다.

- Service에서 값을 받거나 업데이트할 때, getter, setter 메서드를 이용한다.

- 회원 여부 체크는 userId와 accounts의 index값을 통해 하도록 한다.

- 항공권이 예매되면 해당 좌석의 자리를 0으로 변경하고 해당 Air의 index와, 좌석의 index를 입력받는다. 
다음으로, UserService에서 User의 예매내역 객체에 집어넣는 함수를 호출한다.
```

<br/>

---

## 🐬Entity
1. 항공사 예매 정보 (아이디(인덱스)), 항공사, 출발지, 도착지, 출발 시간(년 월 일 시 분), 도착 시간(년 월 일 시 분), 남은(예약 가능한) 좌석 개수, 티켓 가격)

2. User (아이디, 이름, 비밀번호)

---

<br/>

## 🐬커뮤니케이션 다이어그램
<img src = "https://github.com/Team-PPotatos/potatoAirs/assets/109736890/fa2dad0f-a4ee-42b1-8e70-430935e20fea" width="350" height="300"/>
<img src = "https://github.com/Team-PPotatos/potatoAirs/assets/109736890/541678ee-faf5-4de3-99b1-814bdcab6493" width="350" height="300"/> 


---

<br/>

## 🐬이렇게 맡아서 했어요 !
|                                      노소희                                      |                                      장유진                                       |                                      김준열                                       |                                      이한나                                        
| :------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------:  
| <img src="https://avatars.githubusercontent.com/u/109736890?v=4" width="200px" /> | <img src="https://velog.velcdn.com/images/nellroll/post/84b96181-c3f3-4e04-9a6d-121a23035507/image.jpg" width="200px" /> | <img src="https://avatars.githubusercontent.com/u/58796630?s=400&u=67d4699c0f3de32c3a1405b697dc349c0ec09be4&v=4" width="200px" /> | <img src="https://avatars.githubusercontent.com/u/114378724?v=4" width="200px" />
|                      [@SO-HUII](https://github.com/SO-HUII)                       |                      [@jinchiin](https://github.com/jinchiim)                       |                      [@june5228](https://github.com/june5228)                       |                      [@hanna0527](https://github.com/hanna0527)                   
|                               항공사 엔티티 + 유저 입력 구현                                 |                                 항공사 예약 및 예약 취소                        |                               유저 엔티티 + 회원가입                                 |                                 항공사 예약 및 예약 취소                        | 

<br/>

---
## 🌈 프로그램 시연 영상
https://youtu.be/AUc4TlBPSaQ

<br/>

---

## ✅ 커밋 컨벤션

| 제목        | 내용                                                                             |
| ----------- | -------------------------------------------------------------------------------- |
| init        | 작업 초기 세팅 (패키지 설치 등)                                                  |
| feat        | 새로운 기능을 추가할 경우                                                        |
| fix         | 버그 해결                                                                       |
| docs        | 문서를 수정한 경우, 파일 삭제, 파일명 수정 등 ex) README.md                      |
| chore       | 주석 추가, 자잘한 문서 수정                                                      |
| code review | 코드 리뷰 반영 업데이트                                                                   |
