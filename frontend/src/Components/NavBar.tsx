import './NavBar.css';
import React from 'react';
import {Link} from "react-router-dom";

export default function NavBar() {
    return (
        <div className="navbar">
            <Link to={"/"}>
                Start
            </Link>
            <Link to={"/vorrat"}>
                Vorrat
            </Link>
            <Link to={"/login"}>
                Login
            </Link>
            <Link to={"/register"}>
                Registrieren
            </Link>
            <Link to={"/category"}>
                Kategorie
            </Link>
        </div>
    )
}