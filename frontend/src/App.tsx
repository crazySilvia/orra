import React from 'react';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import AuthProvider from "./Context/AuthProvider";
import LoginPage from "./Pages/LoginPage";
import Mainpage from "./Pages/Mainpage";
import RegisterPage from "./Pages/RegisterPage";
import ListPage from "./Pages/ListPage";
import GewuerzePage from "./Pages/Listpages/GewuerzePage";
import TrockenPage from "./Pages/Listpages/TrockenPage";
import GetraenkePage from "./Pages/Listpages/GetraenkePage";
import KonservenPage from "./Pages/Listpages/KonservenPage";
import SelbstgemachtesPage from "./Pages/Listpages/SelbstgemachtPage";
import SonstigesPage from "./Pages/Listpages/SonstigesPage";
import BackenPage from "./Pages/Listpages/Backen";

function App() {
  return (
      <BrowserRouter>
        <AuthProvider>
          <Routes>
              <Route path="/" element={<Mainpage />}/>
              <Route path={"/login"} element={<LoginPage />}/>
              <Route path={"/register"} element={<RegisterPage />}/>
              <Route path={"/vorrat"} element={<ListPage />}/>
              <Route path={"/gewuerze"} element={<GewuerzePage />}/>
              <Route path={"/trocken"} element={<TrockenPage />}/>
              <Route path="/getraenke" element={<GetraenkePage />}/>
              <Route path={"/konserven"} element={<KonservenPage />}/>
              <Route path={"/selbstgemacht"} element={<SelbstgemachtesPage />}/>
              <Route path={"/sonstiges"} element={<SonstigesPage />}/>
              <Route path={"/backen"} element={<BackenPage />}/>
          </ Routes>
        </ AuthProvider>
      </ BrowserRouter>
  );
}

export default App;
