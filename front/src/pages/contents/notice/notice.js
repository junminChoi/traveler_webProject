//pages/Nav.js
import React from "react";
import './notice.css';
import EventFestival from "./eventFestival/eventFestival";
import Banner from "./banner/banner";
import Notion from "./notion/notion";
function notice() {
  return (

    <div className="notice">
      <EventFestival></EventFestival>
      <Banner></Banner>
      <Notion></Notion>
    </div>
  );
}

export default notice;