

import React from "react";
import axios from "axios";


//pages/Nav.js
//import './pageLists.css';


function EventDetail() {
    let type = sessionStorage.getItem("type");
    let title = sessionStorage.getItem("title");
    let eventId = sessionStorage.getItem("eventId");

    axios.get('http://210.115.227.82:18080/api/' + type + '/info?id='+eventId+'&title=' + title)
            .then(function (response) {
                console.log(response.data.response.info);
                document.getElementById("title").innerHTML = " 제목 : " + response.data.response.info.title;
                document.getElementById("content").innerHTML = " 분류 : " + response.data.response.info.content;
                document.getElementById("homepage").innerHTML = " 홈페이지 주소 : " + response.data.response.info.homepage;
                document.getElementById("phoneNumber").innerHTML = " 전화번호 : " + response.data.response.info.phoneNumber;
                document.getElementById("startDate").innerHTML = " 시작일 : " + response.data.response.info.startDate;
                document.getElementById("endDate").innerHTML = " 종료일 : " + response.data.response.info.endDate;
            })
            .catch(function (error) {
                //console.log(error);
            })

    
    return (
        <div className="icons">
            <span id = "title"></span> <br/>
            <span id = "content"></span><br/>
            <span id = "homepage"></span><br/>
            <span id = "phoneNumber"></span><br/>
            <span id = "startDate"></span><br/>
            <span id = "endDate" ></span>
            
        </div>
    );
}

export default EventDetail;