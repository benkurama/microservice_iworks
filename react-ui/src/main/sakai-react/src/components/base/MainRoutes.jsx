import React from "react";
import { Route, Routes} from "react-router";

import Login from "../../pages/Login";
import Home from "../../pages/Home";
import DashboardResponsive from '../../pages/DashboardResponsive';

import PublicRoutes from "./PublicRoutes";
import ProtectedRoutes from "./ProtectedRoutes";
//import Content from "./Content";

const MainRoutes = () => (
    <Routes>

        <Route path="/" element={<ProtectedRoutes roleRequired={"ROLE_SUPER ADMINISTRATOR" | "TECHNICIAN"} />}>
                <Route path="/" element={<Home/>} />
                <Route path="/*" element={<Home/>} />
             
                <Route path="/DashboardResponsive" element={<DashboardResponsive/>} />
        </Route>

        <Route path="login" element={<PublicRoutes />}>
            <Route path="/login" element={<Login/>} />
        </Route>

    </Routes>

);
export default MainRoutes;
