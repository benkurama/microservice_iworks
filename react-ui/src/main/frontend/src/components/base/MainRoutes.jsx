import React from "react";
import {Navigate, Route, Routes} from "react-router";
import Profile from "../Profile";
import Login from "../Login";
import Home from "./Home";
import PublicRoutes from "./PublicRoutes";
import ProtectedRoutes from "./ProtectedRoutes";
import Content from "./Content";

const MainRoutes = () => (
    <Routes>
        {/** Protected Routes */}
        {/** Wrap all Route under ProtectedRoutes element */}
        <Route path="/" element={<ProtectedRoutes />}>
            <Route path="/" element={<Content/>}>
                <Route path="/" element={<Navigate replace to="home" />} />
                <Route path="home" element={<Home/>}/>
                {/*<Route path="dashboard" element={<Dashboard />} />*/}
                <Route path="profile" element={<Profile/>}/>
            </Route>
        </Route>

        {/** Public Routes */}
        {/** Wrap all Route under PublicRoutes element */}
        <Route path="login" element={<PublicRoutes />}>
            <Route path="/login" element={<Login/>} />
        </Route>

        {/** Permission denied route */}
        {/*<Route path="/denied" element={<PermissionDenied />} />*/}
    </Routes>

);
export default MainRoutes;