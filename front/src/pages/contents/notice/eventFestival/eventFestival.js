//pages/Nav.js
import axios from "axios";
import React from "react";
import { Link } from "react-router-dom";
import './eventFestival.css';
function eventFestival() {

  
  //공연행사 찾기
  axios.get('http://210.115.227.82:18080/api/event/list')
        .then(function (response) {
          let i;
          let randomNum;
          let randomIndexArray = []
          for (i=0; i<3; i++) {
            randomNum = Math.floor(Math.random() * 10)
            randomIndexArray.push(randomNum)
          }
          // document.getElementById("con1").innerHTML = response.data.response.list[randomIndexArray[0]].title;

          // document.getElementById("con2").innerHTML = response.data.response.list[randomIndexArray[1]].title;
          // document.getElementById("con3").innerHTML = response.data.response.list[randomIndexArray[2]].title;
        })
        .catch(function (error) {
            console.log(error);
        })
  //   // 문화축제 찾아오기
  // axios.get('http://210.115.227.82:18080/api/festival/list')
  //       .then(function (response) {
  //         let randint2 = Math.floor(Math.random()*10);
  //         document.getElementById("con2").innerHTML = response.data.response.list[randint2].title;
  //       })
  //       .catch(function (error) {
  //           console.log(error);
  //       })
  
  return (

    <div className = "eventFestivalInFunction">
        <p id = "sharpquote">#지금 강원도에서는</p>
        <p id = "hgc">- 행사 . 공연 . 축제 -</p>

        <div id = "conDiv">
          
          <div id = "con1"></div>
          <div id = "con2"></div>
          <div id = "con3"></div>
        </div>
        <Link to = "/eventAndFestival"><div id = "polygon"><div id = "arrow"></div></div></Link>
        

    </div>
  );
}

export default eventFestival;