
import './App.css';
import { BrowserRouter, Route, Switch } from "react-router-dom";
// src/pages/index.js를 통해서 한번에 import 할 수 있도록 함
import Main from "./pages/Main";
import eventAndFestivalPage from "./pages/eventAndFestivalPage";
import weatherInfoPage from './pages/weatherInfoPage';
import EventDetail from './pages/contents/eventAndFestival/details/eventDetail';
import TripSelectionPage from './pages/TripSelectionPage';
import ShowTripData from './pages/contents/trip/showTripData';

function App() {
  
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/" component={Main} />
        <Route exact path="/eventAndFestival" component={eventAndFestivalPage} />
        <Route exact path="/weather" component={weatherInfoPage} />
        <Route exact path="/eventAndFestival/details" component={EventDetail} />
        <Route exact path="/tripSelection" component={TripSelectionPage} />
        <Route exact path="/tripSelection/details" component={ShowTripData} />
      </Switch>
    </BrowserRouter>
  );
}

export default App;

//