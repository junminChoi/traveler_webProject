//pages/Nav.js
import React from "react";
import { Link } from "react-router-dom";
import './tripNearby.css';
import axios from "axios";



function TripNearby() {
    let currentPage = 1;
    let first = 1;
    let last = 5;
    let totalPage= 5;
    let responseData;
    getPageNum();
    getPage();

    //총 페이지 가져오기
    function getPageNum() {
        axios.get('http://210.115.227.82:18080/api/trip/list/totalpage?cnt=8&area=')
            .then(function (response) {
                totalPage = response.data.response.totalPage;
            })
            .catch(function (error) {
                console.log(error);
            })
    }

    function getPage() {
        axios.get('http://210.115.227.82:18080/api/trip/list?cnt=8&num='+currentPage+'&area=')
            .then(function (response) {
                console.log(response);
                responseData = response.data.response;
                PrintDataList(responseData);
            })
            .catch(function (error) {
                //console.log(error);
            })
    }
    
    function PrintDataList(data) {
        for (let i = 0; i < 8; i++) {
            document.getElementById("tPlace" + i).innerHTML = data.list[i].title;
                console.log(i);
            }
            paging(totalPage, currentPage);
    }
    

    //페이징 버튼 구현
    function btn1_click() {
        console.log((first) + "clicked!");
        currentPage = first;
        getPage();
    }
    function btn2_click() {
        //console.log((first + 1) + "clicked!");
        currentPage = first + 1;
        getPage();
    }
    function btn3_click() {
        //console.log((first + 2) + "clicked!");
        currentPage = first + 2;
        getPage();
    }
    function btn4_click() {
        //console.log((first + 3) + "clicked!");
        currentPage = first + 3;
        getPage();
    }
    function btn5_click() {
        currentPage = first + 4;
        getPage();
    }

    function paging(totalPage, currentPage) {
        console.log("toto " + totalPage);
        last = 5;
        if (currentPage < 4) {
            last = 5;
        } else if (currentPage > totalPage - 2) {
            last = totalPage;
        } else {
            last = currentPage + 2;
        }
        first = last - 4;

        
        console.log("first = " + first);                                          
        for (let i = 1; i < 10; i = i + 1 ) {
            document.getElementById("btn" + i).innerHTML = (first + i - 1);

        }
    }

    function titleClick(input) {
        //console.log("res = " + responseData.list[input].title);
        //console.log("loc = " + responseData.list[input].location);
        //sessionStorage.setItem("tripTitle", responseData.list[input].title);
        sessionStorage.setItem("location", responseData.list[input].location);
    
    }
    
    return (
        <div className="Tripcontents">
            <span id = "quote">#내 주변 여행지</span><hr/>
            <div id = "row1">
            <Link to="/tripSelection/details"><div id = "tPlace0" onClick={() => { titleClick('0'); }}></div></Link>
            <Link to="/tripSelection/details"><div id = "tPlace1" onClick={() => { titleClick('1'); }}></div></Link>
            <Link to="/tripSelection/details"><div id = "tPlace2" onClick={() => { titleClick('2'); }}></div></Link>
            <Link to="/tripSelection/details"><div id = "tPlace3" onClick={() => { titleClick('3'); }}></div></Link>

            </div>
            <div id = "row2">
            <Link to="/tripSelection/details"><div id = "tPlace4" onClick={() => { titleClick('4'); }}></div></Link>
            <Link to="/tripSelection/details"><div id = "tPlace5" onClick={() => { titleClick('5'); }}></div></Link>
            <Link to="/tripSelection/details"><div id = "tPlace6" onClick={() => { titleClick('6'); }}></div></Link>
            <Link to="/tripSelection/details"><div id = "tPlace7" onClick={() => { titleClick('7'); }}></div></Link>
            </div>
            
            <div id="pagebtnsinTripComp">
                <button id="btn1" onClick={btn1_click}></button>
                <button id="btn2" onClick={btn2_click}></button>
                <button id="btn3" onClick={btn3_click}></button>
                <button id="btn4" onClick={btn4_click}></button>
                <button id="btn5" onClick={btn5_click}></button>
            </div>
        </div>
    );
}

export default TripNearby;


