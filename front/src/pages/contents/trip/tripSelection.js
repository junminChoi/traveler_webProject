//pages/Nav.js
import React from "react";
import { Link } from "react-router-dom";
import './tripSelection.css';
function TripSelection() {
    return (
        <div className="icons">
            
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

export default TripSelection;