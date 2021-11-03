//pages/Nav.js
import React from "react";
import HeaderMain from "./contents/header/headerMain";
import HeaderMenu from "./contents/header/headerMenu";
import WeatherCast from "./contents/weather/weatherCast";
import Notice from "./contents/notice/notice";
import "./pagecss/weatherInfoPage.css";


function weatherInfoPage() {

  return (
    <div className="eventAndFestivalPageMain">
      <div id="header">
        <HeaderMain />
        <HeaderMenu />
      </div>
      
      <div id="weatherInformations">
        <WeatherCast></WeatherCast>
      </div>

      <div id = "NoticeSection">
            <Notice></Notice>
        </div>

    </div>
  );
}

export default weatherInfoPage;