import React from 'react';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import AuthProvider from "./Context/AuthProvider";
import LoginPage from "./Pages/LoginPage";
import Homepage from "./Pages/Homepage";
import RegisterPage from "./Pages/RegisterPage";
import VorratPage from "./Pages/VorratPage";
import ListPage from "./Pages/Listpages/ListPage";
import DataProvider from "./Context/DataProvider";
import NavBar from "./Components/NavBar";
import RequireAuth from "./Routing/RequireAuth";
import CategoryPage from "./Pages/CategoryPage";

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
                            <VorratPage/>
                        </RequireAuth>
                        }/>
                        <Route path={"/:listId"} element={
                        <RequireAuth>
                            <ListPage/>
                        </RequireAuth>
                        }/>
                        <Route path={"/:categoryId"} element={
                            <RequireAuth>
                                <CategoryPage/>
                            </RequireAuth>
                        }/>
                    </ Routes>
                </DataProvider>
            </ AuthProvider>
        </ BrowserRouter>
    );
}

export default App;