import React, {useEffect, useState} from 'react';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import AuthProvider from "./Context/AuthProvider";
import LoginPage from "./Pages/LoginPage";
import Homepage from "./Pages/Homepage";
import RegisterPage from "./Pages/RegisterPage";
import ListsPage from "./Pages/ListsPage";
import ListPage from "./Pages/Listpages/ListPage";
import {ArtikelList} from "./Model/ArtikelList";
import {listnames} from "./Services/apiService";

function App() {

    const[articleLists, setarticleLists] = useState<ArtikelList[]>([])
    useEffect(() => {listnames().then(setarticleLists)},[])

  return (
      <BrowserRouter>
        <AuthProvider>
          <Routes>
              <Route path="/" element={<Homepage />}/>
              <Route path={"/login"} element={<LoginPage />}/>
              <Route path={"/register"} element={<RegisterPage />}/>
              <Route path={"/vorrat"} element={<ListsPage />}/>
              <Route path={"/:listname"} element={<ListPage allLists={articleLists} />}/>
          </ Routes>
        </ AuthProvider>
      </ BrowserRouter>
  );
}
export default App;