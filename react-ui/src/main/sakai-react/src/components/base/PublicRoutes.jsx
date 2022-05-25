import React from 'react';

import {Navigate, Outlet} from 'react-router-dom'
import AuthService from "../../service/AuthService";

const useAuth=()=>{
    const user= AuthService.getCurrentUser();
    return !!user;
};

const  PublicRoutes=(props:any) =>{

    const auth= useAuth();

    return auth?<Navigate to="/login"/>: <Outlet/>
};

export default PublicRoutes;