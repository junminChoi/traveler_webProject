//pages/Nav.js
import React from "react";
import EventTable from "./contents/eventAndFestival/eventTable";
import HeaderMain from "./contents/header/headerMain";
import HeaderMenu from "./contents/header/headerMenu";
import './contents/eventAndFestival/eventAndFestivalPage.css';

function eventAndFestival() {
  return (
    <div className="eventAndFestivalPageMain">
      <div id="header">
        <HeaderMain />
        <HeaderMenu />
      </div>

      <div id="eventTable">
        <EventTable></EventTable>
      </div>
    </div>
  );
}

export default eventAndFestival;