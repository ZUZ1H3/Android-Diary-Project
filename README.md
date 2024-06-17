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
- \+ 버튼을 누르면 자신의 핸드폰 갤러리에 있는 사진을 첨부할 수 있다.
- 작성 후 저장 버튼을 누르면 데이터베이스에 저장되고 메인 화면으로 돌아간다.

<img src="https://github.com/ZUZ1H3/Android-Diary-Project/assets/147241368/af7cb61d-f94f-4594-afbe-5de17253ec0a" alt="diary2" style="height: 600px;">
<img src="https://github.com/ZUZ1H3/Android-Diary-Project/assets/147241368/2c1b9bff-9ff8-409e-8691-37b15d495148" alt="diary3" style="height: 600px;">


<br/>
<br/>

**일기 리스트 화면**
- 저장된 일기들을 날짜 순으로 볼 수 있다.
- 선택했던 날씨와 감정 이모티콘이 함께 보여진다.
- 사진을 첨부하여 일기를 작성한 경우 작은 크기로 미리 볼 수 있다.
- 일기 하나를 누르면 전체화면으로 볼 수 있다.

<img src="https://github.com/ZUZ1H3/Android-Diary-Project/assets/147241368/53ee0080-ce80-45bb-a3f6-104b1dbb2e0c" alt="diary4" style="height: 600px;">

<img src="https://github.com/ZUZ1H3/Android-Diary-Project/assets/147241368/2dd39807-27f0-4155-b220-22c4b0a8a7a1" alt="diary5" style="height: 600px;">


<br/>
<br/>

**감정 분석 그래프 화면**
-  데이터베이스에 저장된 감정 중, 가장 많이 저장된 감정 Top 3를 보여준다.
- MPAndroidChart를 이용한 원형 그래프로 전체 비율을 시각화한다.

<img src="https://github.com/ZUZ1H3/Android-Diary-Project/assets/147241368/98a3d793-2d1e-43ee-9828-f19e1f223c04" height="600" 
alt="diary6">

<br/>

**도서 검색, 리뷰 작성 화면**
- 네이버 검색 API를 활용하여 제목 또는 저자를 입력하면 도서를 검색할 수 있다.
- \+ 버튼을 누르면 리뷰를 작성할 수 있는 화면으로 넘어간다.
- 작성 후 저장 버튼을 누르면 데이터베이스에 저장된다.
-  '내가 읽은 도서' 화면에서 저장된 도서 리스트를 확인할 수있다.

<img src="https://github.com/ZUZ1H3/Android-Diary-Project/assets/147241368/4398e22d-c518-494f-8785-74ddfea93179" height="600"  alt="diary7">
<img src="https://github.com/ZUZ1H3/Android-Diary-Project/assets/147241368/9216bc9a-f89a-43b7-808a-29b27e445cdb" height="600" 
 alt="diary8">
<img src="https://github.com/ZUZ1H3/Android-Diary-Project/assets/147241368/cbfeefad-1b40-4c72-8142-3518f5e7f425"  height="600"  alt="diary9">

<br/>
<br/>

**배경화면 변경 화면**
- 메인 화면에서 설정 버튼을 누르면 배경화면을 선택할 수 있는 화면으로 넘어간다.
- 선택 후 체크 버튼을 누르면 Toast 메시지가 뜨며 배경화면이 변경된다.

<img src="https://github.com/ZUZ1H3/Android-Diary-Project/assets/147241368/16d648aa-ae17-44ec-b6b3-dbe751c1eeca" height="600"  alt="diary10">
<img src="https://github.com/ZUZ1H3/Android-Diary-Project/assets/147241368/78dcfb8b-e791-4c34-88f0-9f9d359801e3" height="600"  alt="diary11">

## 기대 효과
1. 일기를 간편하게 작성할 수 있다.
2. 사용자 개인의 감정 데이터를 시각화하여 자기 이해도를 높일 수 있다.
3. 독서 기록 기능을 통해 사용자는 자신이 읽었던 도서를 정리하고 되돌아볼 수 있다.
   
