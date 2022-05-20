import React from 'react';

import {useHistory , useRouteMatch } from 'react-router-dom'
import AuthService from "../../service/AuthService";

const useAuth=()=>{
    const user= AuthService.getCurrentUser();
    return !!user;
};

const  PublicRoutes=(props) =>{

    

    const auth= useAuth();

    return <useHistory  to="/login"/>
};

export default PublicRoutes;