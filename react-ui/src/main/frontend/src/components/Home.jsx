import React, {useState, useEffect} from "react";
//import UserResource from "../services/UserResource";

import Sidebar from "./base/Sidebar";
import Header from "./base/Header";

import Graphs from "./graphs-fragment/LineGraph"

import { Button } from 'primereact/button';

const Home = () => {

    
/*
const [ setContent] = useState("");
    useEffect(() => {
        UserResource.getResourceMenu().then(
            (response) => {
                setContent(response.data);
            },
            (error) => {
                const _content =
                    (error.response && error.response.data) ||
                    error.message ||
                    error.toString();
                setContent(_content);
            }
        );
    }, []);

    */

    return (
        <>
            <Header />
            <div className="row">
                <Sidebar />

                <div class="col-md-9">

                    <div id="contentBlock" style={{ padding: "20px" }}>

                        <h3>Welcome to HOME PAGE SECTION</h3>
                        <Button label="DEMOS" />

                        <Graphs />

                    </div>

                </div>


            </div>
        </>
    );
};
export default Home;
