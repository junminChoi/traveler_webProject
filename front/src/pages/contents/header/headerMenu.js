//pages/Nav.js
import React from "react";
import './headerMenu.css';
import { Link } from "react-router-dom";
function headerMenu() {
  return (
    <div>
    <div className = "MenuBar">
    <Link to="/eventAndFestival"><div class = "Menucontents" id = "kangwonNow">지금의 강원</div> </Link> 
    <Link to="/tripSelection"><div class = "Menucontents" id = "kangwonNow">여행지</div> </Link> 
    <Link to="/weather"><div class = "Menucontents" id = "kangwonNow">여행정보</div> </Link> 
    <Link to=""><div class = "Menucontents" id = "kangwonNow">예약하기</div> </Link> 
      <div class="dropdown-content">
        <div class = "rowInMenu"><div id = "MenuContentsInRow">행사 소식</div><div id = "MenuContentsInRow">지역별</div><div id = "MenuContentsInRow">게시판</div><div id = "MenuContentsInRow">예약정보 확인/취소</div></div>
        <div class = "rowInMenu"><div id = "MenuContentsInRow">공연 소식</div><div id = "MenuContentsInRow">내 주변</div><div id = "MenuContentsInRow">여행예보</div><div id = "MenuContentsInRow">지난 예약정보</div></div>
        <div class = "rowInMenu"><div id = "MenuContentsInRow">축제 소식</div><div id = "MenuContentsInRow">액티비티</div><div id = "MenuContentsInRow">여행지 추천</div><div id = "MenuContentsInRow">결제 등록</div></div>
        <div class = "rowInMenu" id = "lastLine"><div id = "MenuContentsInRow">날짜별</div><div id = "MenuContentsInRow">기타</div><div id = "MenuContentsInRow"></div><div id = "MenuContentsInRow"></div></div>
      </div>
      
    </div>


    </div>
  );
}

export default headerMenu;
