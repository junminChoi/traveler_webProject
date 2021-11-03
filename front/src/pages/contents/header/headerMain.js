//pages/Nav.js
import React from "react";
import './headerMain.css';
import { Link } from "react-router-dom";
function headerMain() {
  return (
        <div className = "HeadLine">
          <Link to = "/"><div id = "Logo"></div></Link>
          <Link to = "/weather"><div id = "weatherCast"></div></Link>
          <div id = "search"></div>
          <div id = "logIn"></div>
          <div id = "myPage"></div>
        </div>
    

    
  );
}

export default headerMain;


/*
<div id="HeadLine">
      <div id="Logo">
        <div id="logo-img"></div>
      </div>
      <div id="utilities">
        <div id="weatherCast"></div>
        <div id="search"></div>
        <div id="login"></div>
        <div id="mypage"></div>
      </div>
    </div>

*/