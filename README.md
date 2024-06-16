# 감정 일기 & 독서 기록 안드로이드 앱🦔

## 배경

사회에서 사람들은 스트레스와 다양한 감정을 경험한다. 일기를 작성하면 이러한 감정을 표현하고 정리할 수 있으며, 자신을 더 깊이 이해하는 데 도움이 된다. 하지만 바쁜 일상 속에서 꾸준히 일기를 작성하고 감정을 분석하는 것은 쉽지 않다.

또한, 많은 사람들이 독서를 통해 지식과 즐거움을 얻는다. 독서 기록을 남기면 자신이 읽은 책을 되돌아볼 수 있으며, 독서 경험을 더욱 풍부하게 만들 수 있다. 하지만 읽은 책을 체계적으로 관리하는 것 역시 어렵다.

이러한 배경으로, 감정과 독서 기록을 쉽게 기록하고 관리할 수 있는 앱을 제작해보았다.


## 프로젝트 기능
- **감정 일기 작성**: 캘린더의 날짜를 클릭하면 그 날의 날씨와 9가지 감정 중 하나를 선택하여 일기를 작성할 수 있다.
- **감정 분석 그래프**: 많이 저장된 감정을 Top 3로 순위를 매기고, MPAndroidChart 라이브러리를 사용해 Pie Chart로 시각화하여 보여준다.
- **독서 검색 및 기록**: 네이버 API를 이용해 도서를 검색하고, 독후감을 기록할 수 있다.
- 사용자가 5가지 배경화면 중 하나를 선택하여 변경할 수 있다.

## 프로젝트 구조
<img src="https://github.com/ZUZ1H3/Android-Diary-Project/assets/147241368/1e632352-f25e-44b0-b149-6a4c91201d5a" alt="diary12" width="600">

## 결과물

<br/>


**메인화면**
- 날짜를 누르면 일기를 작성할 수 있다.
- 일기를 작성한 날짜에는 저장된 감정 이모티콘이 표시된다.



<img src="https://github.com/ZUZ1H3/Android-Diary-Project/assets/147241368/7600d814-10b5-427e-af17-7719c30e5107" alt="diary1" height="600">

<br/>
<br/>

**일기 작성화면**
- 그날의 날씨와 감정을 선택할 수 있다.
- -> 버튼을 누르면 일기를 작성하는 화면으로 넘어간다.
- 작성 후 저장 버튼을 누르면 데이터베이스에 저장되고 메인 화면으로 돌아간다.

<img src="https://github.com/ZUZ1H3/Android-Diary-Project/assets/147241368/13b6a35d-dd9a-48b4-b108-c2ad27a3ff8c" height="600" alt="diary2">
<img src="https://github.com/ZUZ1H3/Android-Diary-Project/assets/147241368/365de5b9-5866-4310-b500-ee52cf623d53"  height="600" alt="diary3">

<br/>
<br/>

**일기 리스트 화면**
- 저장된 일기들을 날짜 순으로 볼 수 있다.
- 일기 하나를 누르면 자세히 볼 수 있다.

<img src="https://github.com/ZUZ1H3/Android-Diary-Project/assets/147241368/3d275f2a-2e54-464c-b05e-03b59dc0152e" height="600" alt="diary4">
<img src="https://github.com/ZUZ1H3/Android-Diary-Project/assets/147241368/88578df7-f580-4061-a57c-7c8f3631d1b2" height="600" 고 배경화면이 변경된다.

<img src="https://github.com/ZUZ1H3/Android-Diary-Project/assets/147241368/16d648aa-ae17-44ec-b6b3-dbe751c1eeca" height="600"  alt="diary10">
<img src="https://github.com/ZUZ1H3/Android-Diary-Project/assets/147241368/78dcfb8b-e791-4c34-88f0-9f9d359801e3" height="600"  alt="diary11">

## 기대 효과
1. 일기를 간편하게 작성할 수 있다.
2. 사용자 개인의 감정 데이터를 시각화하여 자기 이해도를 높일 수 있다.
3. 독서 기록 기능을 통해 사용자는 자신이 읽었던 도서를 정리하고 되돌아볼 수 있다.
   
