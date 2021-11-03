//pages/Nav.js
import React from "react";
import { Link } from "react-router-dom";
import './pageLists.css';
function pagelist() {
    return (
        <div className="icons">
            <div id="eventAndFestival">
            </div>
            <Link to={{
                pathname: `/tripSelection`,
            }}> <div id="tripPlaceNearby">
                </div></Link>
            <div id="tripPlacebyArea">
            </div>
            <div id="tripPlaceRecom">
            </div>
            <div id="weatherAnnounce">
            </div>
        </div>
    );
}

export default pagelist;