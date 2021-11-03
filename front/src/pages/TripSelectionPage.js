//pages/Nav.js
import React from "react";
import HeaderMain from "./contents/header/headerMain";
import HeaderMenu from "./contents/header/headerMenu";
import TripSelection from "./contents/trip/tripSelection";
import TripNearby from "./contents/trip/tripNearby";
import Notice from "./contents/notice/notice";

import "./pagecss/TripSelectionPage.css";


function TripSelectionPage() {

    return (
        <div className="Tripselection">
            <div id="header">
                <HeaderMain />
                <HeaderMenu />
            </div>

            <div id="pagelists">
                <TripSelection />
            </div>

            <div id="tripPlaces">
                <TripNearby>
                </TripNearby>
            </div>

            <div id="NoticeSection">
                <Notice></Notice>
            </div>

        </div>
    );
}

export default TripSelectionPage;