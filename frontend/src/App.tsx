import React from 'react';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import AuthProvider from "./Context/AuthProvider";
import LoginPage from "./Pages/LoginPage";
import Mainpage from "./Pages/Mainpage";

function App() {
  return (
      <BrowserRouter>
        <AuthProvider>
          <Routes>
            <Route path="/" element={<Mainpage />}/>
            <Route path={"/login"} element={<LoginPage />}/>
          </ Routes>
        </ AuthProvider>
      </ BrowserRouter>
  );
}

export default App;
