import React from "react"

import {Navigate, Outlet} from "react-router-dom"
import AuthService from "../../service/AuthService";

const useAuth = () => {
    //get item from localstorage
    const _user = AuthService.getCurrentUser();

    //console.log(_user.roles);

    if (_user) {
        return {
            auth: true,
            role: _user.roles,
        }
    } else {
        return {
            auth: false,
            role: null,
        }
    }
};

//protected Route state
type ProtectedRouteType = {
    //roleRequired?: "ADMIN" | "USER"
    roleRequired ?: "ROLE_SUPER ADMINISTRATOR" | "TECHNICIAN"
}

const ProtectedRoutes = (props: ProtectedRouteType) => {
    const {auth, role} = useAuth();

    console.log("ProtectedRouteType");
    //console.log(role[0]);
    
    //if the role required is there or not
    if (props.roleRequired) {
        console.log("roleRequired");
        return auth ? (
            props.roleRequired === role[0] ? (<Outlet /> ) : ( <Navigate to="/denied" /> )
        ) : 
            ( <Navigate to="/login" />  )
    } else {
        console.log("else");
        return auth ? <Outlet /> : <Navigate to="/login" />
    }
};

export default ProtectedRoutes;