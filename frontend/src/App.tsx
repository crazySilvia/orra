import React from 'react';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import AuthProvider from "./Context/AuthProvider";
import LoginPage from "./Pages/LoginPage";
import Homepage from "./Pages/Homepage";
import RegisterPage from "./Pages/RegisterPage";
import ListsPage from "./Pages/ListsPage";
import ListPage from "./Pages/Listpages/ListPage";
import DataProvider from "./Context/DataProvider";
import NavBar from "./Components/NavBar";
import RecipesPage from "./Pages/RecipesPage";
import RequireAuth from "./Routing/RequireAuth";

function App() {

    return (
        <BrowserRouter>
            <AuthProvider>
                <DataProvider>
                    <NavBar/>
                    <Routes>
                        <Route path="/" element={<Homepage/>}/>
                        <Route path={"/login"} element={<LoginPage/>}/>
                        <Route path={"/register"} element={<RegisterPage/>}/>
                        <Route path={"/vorrat"} element={
                        <RequireAuth>
                            <ListsPage/>
                        </RequireAuth>
                        }/>
                        <Route path={"/:listid"} element={
                        <RequireAuth>
                            <ListPage/>
                        </RequireAuth>
                        }/>
                        <Route path={"/rezepte"} element={
                        <RequireAuth>
                            <RecipesPage/>
                        </RequireAuth>
                        }/>
                    </ Routes>
                </DataProvider>
            </ AuthProvider>
        </ BrowserRouter>
    );
}

export default App;