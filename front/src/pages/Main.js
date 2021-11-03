//pages/Nav.js
import React from "react";
import WeatherCast from "./contents/weather/weatherCast";
import Notice from "./contents/notice/notice";
import HeaderMain from "./contents/header/headerMain";
import HeaderMenu from "./contents/header/headerMenu";
import SeasonProm from "./contents/onMainPage/seasonProm";
import BestTrip from "./contents/onMainPage/bestTrip";
import Pagelist from "./contents/onMainPage/pageLists";
import SearchInfo from "./contents/onMainPage/searchInfo";

import './Main.css';
function Main() {
  return (

    <div className = "MainPage">

        <div id = "Header">
            <HeaderMain></HeaderMain>
            <HeaderMenu></HeaderMenu>
        </div>        


        <div id = "seasonPromotion">
            <SeasonProm></SeasonProm>
        </div>

        <div id = "bestTripRecom">
            <BestTrip></BestTrip>
        </div>

        <div id = "pagelist">
            <Pagelist></Pagelist>
        </div>

        <div id = "NoticeSection">
            <Notice></Notice>
        </div>

        <div id = "TripInformation">
            <div id="TripInfoTitle"> &lt; 여행 정보 BEST 게시글 &gt;</div>
            <div class="info" id="infoBox1">
                <div className="info_inner">
                    코로나로 떠나지 못하는<br/>해외여행을<br/>강원도 양양에서<br/><br/>
                    "SURFYY BEACH"
                </div>
            </div>
            <div class="info" id="infoBox2">
                <div className="info_inner">
                    강원도 야경 맛집<br/><br/>
                    BEST 5!
                </div>
            </div>
            <div class="info" id="infoBox3">
                <div className="info_inner">
                    이색 퓨전 요리<br/>춘천 양식당<br/>맛집 정리
                </div>
            </div>
            <div class="info" id="infoBox4">
                <div className="info_inner">
                    강원도<br/>문화 역사 유적지<br/>여행 코스 추천
                </div>
            </div>

        </div>
        
        <div id = "weatherComp">
            <WeatherCast></WeatherCast> 
        </div>
         
        <div id = "searchInfo">
            <SearchInfo></SearchInfo>
        </div>
        
         
    </div>
  );
}

export default Main;