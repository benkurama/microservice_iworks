import React from "react";
import {Navigate, Route, Routes} from "react-router";

import Login from "../../pages/Login";
import Home from "../../pages/Home";
import PublicRoutes from "./PublicRoutes";
import ProtectedRoutes from "./ProtectedRoutes";
//import Content from "./Content";

const MainRoutes = () => (
    <Routes>

        <Route path="/" element={<ProtectedRoutes />}>
            
                <Route path="/" element={<Navigate replace to="home" />} />
                <Route path="home/*" element={<Home/>} />
             
            
        </Route>


        <Route path="login" element={<PublicRoutes />}>
            <Route path="/login" element={<Login/>} />
        </Route>

    </Routes>

);
export default MainRoutes;