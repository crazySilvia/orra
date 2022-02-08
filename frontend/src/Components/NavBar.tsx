import './NavBar.css';
import React from 'react';

export default function NavBar() {
    return (
        <div className="navbar">
            <a href={"/"}>Start</a>
            <a href={"/vorrat"}>Vorrat</a>
            <a href={"/login"}>Login</a>
            <a href={"/register"}>Registrieren</a>
        </div>
    )
}