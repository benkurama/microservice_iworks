import React from "react";
import {Navigate, Route, Switch} from "react-router";
import Login from "../../pages/Login";
import Home from "../../pages/Home"
import PublicRoutes from "./PublicRoutes";
//import ProtectedRoutes from "./ProtectedRoutes";
//import Content from "./Content";

const MainRoutes = () => (
    <Switch>

        {/*<Route path="/" element={<ProtectedRoutes />}>
            
                <Route path="/" element={<Navigate replace to="home" />} />
                <Route path="home" element={<Home/>}/>
            
        </Route>*/}


        <Route >
            <Route path="/login" element={<Login/>} />
        </Route>


    </Switch>

);
export default MainRoutes;