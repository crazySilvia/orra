import './NavBar.css';
import React from 'react';
import {Link} from "react-router-dom";

export default function NavBar() {
    return (
        <div className="navbar">
            <Link to={"/"}>
                <input className="button" value={"Start"} />
            </Link>
            <Link to={"/vorrat"}>
                <input className="button" value={"Vorrat"} />
            </Link>
            <Link to={"/login"}>
                <input className="button" value={"Login"} />
            </Link>
            <Link to={"/register"}>
                <input className="button" value={"Registrieren"} />
            </Link>
        </div>
    )
}