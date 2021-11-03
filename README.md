<h1 align="center">Welcome to DH-Fitness <img src="https://raw.githubusercontent.com/MartinHeinz/MartinHeinz/master/wave.gif" width="48px"></h1>
<p>
</p>

<center>
    <img src="app/src/main/res/mipmap-xxxhdpi/ic_launcher.png" alt="MODU" style="zoom:76%;" align="center"/>
</center>



> DH-Fitness (2021 울산대학교 안드로이드 term프로젝트)

### 🏠 [Github](https://github.com/jesuisjavert/MODU) :clapper:[Demo 시연영상](https://www.youtube.com/watch?v=JnYyQUX-lPw&feature=youtu.be) :page_with_curl:[프로젝트 명세서](https://drive.google.com/file/d/1fWqLAJYHXRDeOOPT6kzg8ESiNVkAZr1h/view?usp=sharing) :microphone:[Presentation](https://docs.google.com/presentation/d/1onaYFSkInPrBcYtSIOaAIKdrtNF4GdVa/edit?usp=sharing&ouid=103120028218729581019&rtpof=true&sd=true)

<br>

## ✨ Description

```sh
DH Fitness는 push-up 갯수를 카운팅, jogging step수 카운팅 및 운동 기록을 저장하는 어플입니다.
```



## :pushpin: Project Goal

```sh
 코로나가 터져 확진자가  늘어나고 고강도 사회적 거리두기가 시행되면서 집에서 있는 시간이 증가하여 살이 확 찐 자가 증가하였습니다. 저는 계속된 확 찐 자 증가하는 모습을 지켜 볼 수가 없어 운동 앱을 제작하였습니다. 
```



## :mag: 서비스 기능

<center>
    <img src="./README.assets/requirements.png" alt="MODU"  align="center"/>
</center>

## :mag: Overview

### 1. 유저 로그인 페이지 (User Login Page) (소셜로그인)

<center>
    <img src="./README.assets/1.png" alt="MODU"/>
</center>
Kakao API를 이용 소셜 로그인 (OAuth)

<br>

### 2. 트레이너-고객 실시간채팅 Trainer - Client Chatting 

<center>
    <img src="./README.assets/2.png" alt="MODU"/>
</center>
Node.js (Express Framework) 서버 Socket.io 통신으로 1:1 채팅 기능 구현

<br>

### 3. 트레이너-프로그램 추천 시스템 (Best Trainer/Program Carousel)

<center>
    <img src="./README.assets/3.png" alt="MODU"/>
</center>
PT 프로그램 CRUD 기능 및 Carousel 화면 구성

<br>

### 4. 다중 화상통화 기능활용 온라인 홈트레이닝 세션 (Multiple User WebRTC(Realtime Camera) Online Personal Training)

<center>
    <img src="./README.assets/4.png" alt="MODU"/>
</center>
webRTC API 활용 실시간 다자간 영상채팅 기능

<br>

### 5. 달력 기능과 연동한 스케쥴 관리(Schedule managment)

<center>
    <img src="./README.assets/5.png" alt="MODU"/>
</center>
달력 페이지와 트레이너의 PT 프로그램 일정 및 시간 연동 CRUD & My Profile Page

<br>

### 6. 트레이너의 PT관리 CRUD(PT Program & Client management)

<center>
    <img src="./README.assets/6.png" alt="MODU"/>
</center>
관리 중인 PT 프로그램과 회원 관리  My Page

<br>

### 7. PT프로그램 디테일 CRUD(PT Program CRUD)

<center>
    <img src="./README.assets/7.png" alt="MODU"/>
</center>
PT Program 등록 CRUD (일정별 시간 관리)

<br>

### 8. 결제시스템 (카카오 페이 결제) Client Payment System

<center>
    <img src="./README.assets/8.png" alt="MODU"/>
</center>
KakaoPay API 활용 PT 프로그램 결제 시스템, 결제 이후 캘린더로 일정 동기화

<br>

### 9. 실시간 알림 & 프로그램 리뷰 & 예약 시스템

### Realtime Notification & Review & Reservation System

<center>
    <img src="./README.assets/9.png" alt="MODU"/>
</center>
트레이너와 회원간의 실시간 알림 기능 - Program별 리뷰 및 후기 댓글 기능

<br>



## :wrench: Tech Stack

### Tech Stack

<center>
    <img src="./README.assets/stack.png" alt="MODU"/>
</center>



### System Architecture

<center>
    <img src="./README.assets/arch.png" alt="MODU"/>
</center>

<br>

## :pencil2: ERD

<center>
    <img src="./README.assets/erd.png" alt="MODU"/>
</center>
<br>

## :runner: Steps to run

### Backend

```bash
$ cd modeling
$ python -m venv venv
$ source venv/Scripts/activate
$ python install -r requirements.txt
$ python manage.py runserver
```

### Frontend

```bash
$ cd frontend
$ npm install
$ npm run serve
```

<br>

## 🤼‍♂️Author

Team Leader : 🐯**Kang Donghoon**

Backend : 🐶 **Lee Changwan**

Backend: 🐺 **Bae Yongkyun**

Frontend : 🐱 **Kang Byungkook**

Frontend : 🦁 **Roh Hyunsuk**

<hr>

## :trophy: Awards

- SSAFY 자율 프로젝트 우수팀 선정 및 부상 100만원 수상

![Award](./README.assets/MODU.jpg)

- SSAFY  Best Member 선정 & 50000 마일리지 수여

  ![bestmember](./README.assets/bestmember.png)




## 📝 License

Copyright © 2020  MODU's Health  <br>
