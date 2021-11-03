//pages/Nav.js
import React from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import "./eventTable.css";



function EventTable() {



    let currentPage = 1;
    let totalPage = 1;
    let first;
    let last;
    let isEvent = true;
    let responseData;
    getPageNum();
    getPage();

    //총 페이지를 가져오는 데이터

    function getPageNum() {
        let type;
        if (isEvent) {
            type = "event";
            sessionStorage.setItem("type", "event");
        } else {
            type = "festival"
            sessionStorage.setItem("type", "festival");
        }
        axios.get('http://210.115.227.82:18080/api/' + type + '/list/totalpage')
            .then(function (response) {
                //console.log(response.data.response.list[0]);

                totalPage = response.data.response.count;
                console.log("tot : " + totalPage);

            })
            .catch(function (error) {
                console.log(error);
            })
    }

    //공연행사 / 문화축제 클릭 시
    function EventCheck() {
        let btnEvent = document.getElementById('selectEvent');
        let btnFest = document.getElementById('selectFest');
        btnEvent.disabled = true;
        btnFest.disabled = false;
        isEvent = true;
        //console.log(isEvent);
        currentPage = 1;
        getPageNum();
        getPage();
    }

    function FestivalCheck() {
        let btnEvent = document.getElementById('selectEvent');
        let btnFest = document.getElementById('selectFest');
        btnEvent.disabled = false;
        btnFest.disabled = true;
        isEvent = false;
        //console.log(isEvent);
        currentPage = 1;
        getPageNum();
        getPage();
    }



    //페이지 가져오기. api에서 필요한 데이터를 페이지로 가져옴
    function getPage() {
        let type;
        if (isEvent) {
            //console.log("true");
            type = "event";
        } else {
            type = "festival";
        }
        axios.get('http://210.115.227.82:18080/api/' + type + '/list?cnt=10&num=' + currentPage)
            .then(function (response) {
                responseData = response.data.response;
                //console.log(responseData);
                PrintDataList(response.data.response);
            })
            .catch(function (error) {
                //console.log(error);
            })
    }

    //페이징 버튼 구현
    function btn1_click() {
        //console.log((first) + "clicked!");
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
        //console.log((first + 4) + "clicked!");
        currentPage = first + 4;
        getPage();
    }


    //data 화면에 출력
    function PrintDataList(data) {

        for (let i = 0; i < 10; i++) {
            document.getElementById("tdName" + i).innerHTML = data.list[i].title;
            document.getElementById("tdStart" + i).innerHTML = data.list[i].startDate;
            document.getElementById("tdEnd" + i).innerHTML = data.list[i].endDate;
        }
        paging(totalPage, currentPage);
    }


    function paging(totalPage, currentPage) {
        last = 5;
        if (currentPage < 4) {
            last = 5;
        } else if (currentPage > totalPage - 2) {
            last = totalPage;
        } else {
            last = currentPage + 2;
        }
        first = last - 4;


        //console.log("first = " + first);
        for (let i = 1; i < 6; i++) {
            document.getElementById("btn" + i).innerHTML = (first + i - 1);
        }
    }

    function titleClick(input) {
        console.log("res = " + responseData.list[input].title);
        console.log("res = " + responseData.list[input].eventId);
        sessionStorage.setItem("title", responseData.list[input].title);
        sessionStorage.setItem("eventId", responseData.list[input].eventId);

    }


    return (

        <div className="tablesandPageNums">
            <button id="selectEvent" onClick={EventCheck}> 공연 행사 </button>
            <button id="selectFest" onClick={FestivalCheck}> 문화 축제 </button>
            <table id="eventTable">
                <thead>
                    <tr>
                        <th>공연/행사명</th><th>시작일자</th><th>종료일자</th>
                    </tr>

                </thead>
                <tbody>
                    <header></header>
                    <tr>
                        <Link to="/eventAndFestival/details"><td id="tdName0" onClick={() => { titleClick('0'); }}></td></Link>
                        <td id="tdStart0"></td><td id="tdEnd0"></td>
                    </tr>
                    <tr>
                        <Link to="/eventAndFestival/details"><td id="tdName1" onClick={() => { titleClick('1'); }}></td></Link>
                        <td id="tdStart1"></td><td id="tdEnd1"></td>
                    </tr>
                    <tr>
                        <Link to="/eventAndFestival/details"><td id="tdName2" onClick={() => { titleClick('2'); }}></td></Link><td id="tdStart2"></td><td id="tdEnd2"></td>
                    </tr>
                    <tr>
                        <Link to="/eventAndFestival/details"><td id="tdName3" onClick={() => { titleClick('3'); }}></td></Link><td id="tdStart3"></td><td id="tdEnd3"></td>
                    </tr>
                    <tr>
                        <Link to="/eventAndFestival/details"><td id="tdName4" onClick={() => { titleClick('4'); }}></td></Link><td id="tdStart4"></td><td id="tdEnd4"></td>
                    </tr>
                    <tr>
                        <Link to="/eventAndFestival/details"><td id="tdName5" onClick={() => { titleClick('5'); }}></td></Link><td id="tdStart5"></td><td id="tdEnd5"></td>
                    </tr>
                    <tr>
                        <Link to="/eventAndFestival/details"><td id="tdName6" onClick={() => { titleClick('6'); }}></td></Link><td id="tdStart6"></td><td id="tdEnd6"></td>
                    </tr>
                    <tr>
                        <Link to="/eventAndFestival/details"><td id="tdName7" onClick={() => { titleClick('7'); }}></td></Link><td id="tdStart7"></td><td id="tdEnd7"></td>
                    </tr>
                    <tr>
                        <Link to="/eventAndFestival/details"><td id="tdName8" onClick={() => { titleClick('8'); }}></td></Link><td id="tdStart8"></td><td id="tdEnd8"></td>
                    </tr>
                    <tr>
                        <Link to="/eventAndFestival/details"><td id="tdName9" onClick={() => { titleClick('9'); }}></td></Link><td id="tdStart9"></td><td id="tdEnd9"></td>
                    </tr>

                </tbody>
            </table>
            <div id="pagebtns">
                <button id="btn1" onClick={btn1_click}></button>
                <button id="btn2" onClick={btn2_click}></button>
                <button id="btn3" onClick={btn3_click}></button>
                <button id="btn4" onClick={btn4_click}></button>
                <button id="btn5" onClick={btn5_click}></button>
            </div>



        </div>

    );
}

export default EventTable;

/*

<Link to={{
                            pathname: `/eventAndFestival/details`,
                            state: { pagenum: totalPage },

                        }}></Link>
*/