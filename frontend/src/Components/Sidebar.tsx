import React from 'react';
import {Link} from "react-router-dom";

export default function Sidebar() {

    return(
        <div className={"backen"}>
            <Link to={"/backen"}>
                <input type="button" value={"Backen"} />
            </Link>
            <Link to={"/getraenke"}>
                <input type="button" value={"Getränke"} />
            </Link>
            <Link to={"/gewuerze"}>
                <input type="button" value={"Gewürze"} />
            </Link>
            <Link to={"/konserven"}>
                <input type="button" value={"Konserven"} />
            </Link>
            <Link to={"/selbstgemacht"}>
                <input type="button" value={"Selbstgemacht"} />
            </Link>
            <Link to={"/trocken"}>
                <input type="button" value={"Trocken"} />
            </Link>
            <Link to={"/sonstiges"}>
                <input type="button" value={"Sonstiges"} />
            </Link>
        </div>
    )
}