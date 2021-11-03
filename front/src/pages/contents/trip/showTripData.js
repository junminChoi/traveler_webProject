//pages/Nav.js
import React from "react";
import axios from "axios";
import HeaderMain from "../header/headerMain";
import HeaderMenu from "../header/headerMenu";
import TripSelection from "./tripSelection";
import './showTripData.css';


function ShowTripData() {
    let location = sessionStorage.getItem("location");

    console.log("loc : " + location)

    getDetailData();

    function getDetailData() {
        axios.get('http://210.115.227.82:18080/api/trip/info?loc=' + location)
            .then(function (response) {
                console.log(response.data.response);
                insertData(response.data.response);
            })
            .catch(function (error) {
                console.log(error);
            })
    }

    function insertData(data){
        document.getElementById("detailHead").innerHTML = "#" + data.info.title;
        document.getElementById("detailTitle").innerHTML = " | " + data.info.title + " | ";
        document.getElementById("contentText").innerHTML = data.info.content;
        document.getElementById("loc").innerHTML = data.info.location;
        document.getElementById("Phone").innerHTML = data.info.phoneNum;
    }
    return (
        <div className="Details">
        <div id="header">
            <HeaderMain />
            <HeaderMenu />
        </div>

        <div id="pagelists">
            <TripSelection />
        </div>

        <div id="detail">
            <div id = "detailHead"></div>
            <div id = "detailTitle"></div>
            <div id = "pic"><div id = "placeImg" ></div></div>
            <div id = "contentText"></div>
            <div id = "detAndLoc">
            <div id = "detailInfo">#상세 정보 <div id = "innerBoxL">
                <div id = "lines"><div id = "line1">주  소</div>|<div class = "responses" id = "loc"></div></div>
                <div id = "lines"><div id = "line1">연락처</div>|<div class = "responses" id = "Phone"></div></div>
                <div id = "lines"><div id = "line1">휴  일</div>|<div class = "responses" id = "rest">매주 일요일</div></div>
                <div id = "lines"><div id = "line1">입장료</div>|<div class = "responses" id = "Payment">-</div></div>
                <div id = "lines"><div id = "line1">주차시설</div>|<div class = "responses" id = "Parking">-</div></div>
                <div id = "lines"><div id = "line1">홈페이지</div>|<div class = "responses" id = "Homepage"></div></div>
                </div></div>
            <div id = "location">#위치 <div id = "innerBoxR"></div></div>
            </div>
            
        </div>



    </div>

    );
}

export default ShowTripData;