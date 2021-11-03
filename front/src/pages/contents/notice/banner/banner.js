//pages/Nav.js
import React from "react";
import './banner.css';
function banner() {
  function gotoBannerInfo(){
    window.location.href = "https://www.provin.gangwon.kr/gw/portal/sub07_12_04_08_01";
  }
  return (

    <div id = "banner" onClick = {gotoBannerInfo}>
        
    </div>
  );
}

export default banner;