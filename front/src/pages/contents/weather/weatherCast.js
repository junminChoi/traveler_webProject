import axios from "axios";
import React from 'react';
import './weatherCast.css';

function WeatherCast() {
    let dates;
    //일단 보여줌.
    showWeather('?regId=11D10401', '?regId=11D20000');
    //날짜 출력
    console.log("date : " + (new Date().getDate()));

    //출력 날짜 세팅

    axios.get('http://210.115.227.82:18080/api/weather/midta')
            .then(function (response) {
                //let taMin = response.data.response.item[0];
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            })


    function getDate(){
        var today = new Date();
        dates = new Date(today.setDate(today.getDate() + 2))
        for (let i = 3; i < 11; i++) {
            dates = new Date(today.setDate(today.getDate() + 1));
            //document.getElementById("date" + i).innerHTML = dates.getDate() + "일";
        }
    }
    /*
        중기기온조회 / 중기육상예보조회 공통
        영동 / 영서로 나누어 데이터를 보여주는 부분
    */

    //영동 클릭 시 함수
    function EastCheck() {
        let btnEst = document.getElementById('east');
        let btnWst = document.getElementById('west');
        btnEst.disabled = true;
        btnWst.disabled = false;
        showWeather('?regId=11D10401', '?regId=11D20000');
    }
    //영서 클릭 시 함수
    function WestCheck() {
        let btnEst = document.getElementById('east');
        let btnWst = document.getElementById('west');
        btnEst.disabled = false;
        btnWst.disabled = true;
        showWeather('?regId=11D10301', '?regId=11D10000');
    }

    //클릭 후 날씨 출력부분
    function showWeather(regId1, regId2) {
        getDate();
        //중기 기온 조회
        axios.get('http://210.115.227.82:18080/api/weather/midta' + regId1)
            .then(function (response) {
                let taMin = response.data.response.item[0];
                for (let i = 3; i < 11; i++) {
                    document.getElementById("date" + i).innerHTML = dates.getDate() + "일";
                }
                document.getElementById("temp3").innerHTML = taMin.taMin3 + "° / " + taMin.taMax3 + "°";
                document.getElementById("temp4").innerHTML = taMin.taMin4 + "° / " + taMin.taMax4 + "°";
                document.getElementById("temp5").innerHTML = taMin.taMin5 + "° / " + taMin.taMax5 + "°";
                document.getElementById("temp6").innerHTML = taMin.taMin6 + "° / " + taMin.taMax6 + "°";
                document.getElementById("temp7").innerHTML = taMin.taMin7 + "° / " + taMin.taMax7 + "°";
                document.getElementById("temp8").innerHTML = taMin.taMin8 + "° / " + taMin.taMax8 + "°";
                document.getElementById("temp9").innerHTML = taMin.taMin9 + "° / " + taMin.taMax9 + "°";
                document.getElementById("temp10").innerHTML = taMin.taMin10 + "° / " + taMin.taMax10 + "°";
            })
            .catch(function (error) {
                console.log(error);
            })
        //중기 육상예보조회

        axios.get('http://210.115.227.82:18080/api/weather/midlandfcst' + regId2)
            .then(function (response) {
                let rainRate = response.data.response.item[0];
                document.getElementById("rain3").innerHTML = rainRate.rnSt3Pm + "%";
                document.getElementById("rain4").innerHTML = rainRate.rnSt4Pm + "%";
                document.getElementById("rain5").innerHTML = rainRate.rnSt5Pm + "%";
                document.getElementById("rain6").innerHTML = rainRate.rnSt6Pm + "%";
                document.getElementById("rain7").innerHTML = rainRate.rnSt7Pm + "%";
                document.getElementById("rain8").innerHTML = rainRate.rnSt8 + "%";
                document.getElementById("rain9").innerHTML = rainRate.rnSt9 + "%";
                document.getElementById("rain10").innerHTML = rainRate.rnSt10 + "%";

                document.getElementById("weather3").innerHTML = rainRate.wf3Pm;
                document.getElementById("weather4").innerHTML = rainRate.wf4Pm;
                document.getElementById("weather5").innerHTML = rainRate.wf5Pm;
                document.getElementById("weather6").innerHTML = rainRate.wf6Pm;
                document.getElementById("weather7").innerHTML = rainRate.wf7Pm;
                document.getElementById("weather8").innerHTML = rainRate.wf8;
                document.getElementById("weather9").innerHTML = rainRate.wf9;
                document.getElementById("weather10").innerHTML = rainRate.wf10;
            })
            .catch(function (error) {
                console.log(error);
            })

    }



    /*
        기상청 중기전망 조회부분
        중기전망 api데이터 수집 및 데이터 글자를 잘라 가공 후 변수로 제공
    */
    //weatherCast 글자 자르기
    function cutCastQuote(str) {
        let data = str.split('○');
        let rain, temp, sea, weekend;
        rain = data[1];
        temp = data[2];
        sea = data[3];
        weekend = data[4];
        document.getElementById('weather').innerHTML = (rain + "<br>" + temp + "<br>" + sea + "<br>" + weekend + "<br>");
    }

    // //기상청 중기전망조회
    // axios.get('http://210.115.227.82:18080/api/weather/midfcst')
    //     .then(function (response) {
    //         let weatherdata = response.data.response.wfSv;
    //         //글자 자르고 weather div.innerHTml 에 연결
    //         cutCastQuote(weatherdata);
    //         //console.log(weatherdata);
    //     })
    //     .catch(function (error) {
    //         console.log(error);
    //     })

    //필요데이터 > 일단은 글자로 보여줌, 추후 디자이너와 합의 필요.
    return (
        <div id="weatherDIV">
            <button id="east" onClick={EastCheck}> 영동 </button>
            <button id="west" onClick={WestCheck}> 영서 </button>
            <div id = "forecast"></div>
            <div className="weatherConsole">
                <table id="weathertable">
                    <tr>
                        <td class = "date" id="date3"></td><td class = "weather" id="weather3"></td><td class = "temp" id="temp3"></td><td class = "rain" id="rain3"></td>
                    </tr>
                    <tr>
                        <td class = "date" id="date4"></td><td class = "weather" id="weather4"></td><td class = "temp" id="temp4"></td><td class = "rain" id="rain4"></td>
                    </tr>
                    <tr>
                        <td class = "date" id="date5"></td><td class = "weather" id="weather5"></td><td class = "temp" id="temp5"></td><td class = "rain" id="rain5"></td>
                    </tr>
                    <tr>
                        <td class = "date" id="date6"></td><td class = "weather" id="weather6"></td><td class = "temp" id="temp6"></td><td class = "rain" id="rain6"></td>
                    </tr>
                    <tr>
                        <td class = "date" id="date7"></td><td class = "weather" id="weather7"></td><td class = "temp" id="temp7"></td><td class = "rain" id="rain7"></td>
                    </tr>
                    <tr>
                        <td class = "date" id="date8"></td><td class = "weather" id="weather8"></td><td class = "temp" id="temp8"></td><td class = "rain" id="rain8"></td>
                    </tr>
                    <tr>
                        <td class = "date" id="date9"></td><td class = "weather" id="weather9"></td><td class = "temp" id="temp9"></td><td class = "rain" id="rain9"></td>
                    </tr>
                    <tr>
                        <td class = "date" id="date10"></td><td class = "weather" id="weather10"></td><td class = "temp" id="temp10"></td><td class = "rain" id="rain10"></td>
                    </tr>
                </table>
            </div>



        </div>
    );
}



export default WeatherCast;
