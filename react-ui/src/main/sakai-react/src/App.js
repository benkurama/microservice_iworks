//import Home from "./pages/Home";
//import Login from "./pages/Login";
import React from "react"
import MainRoutes from "./components/base/MainRoutes";
import ProtectedRoute from "./components/base/ProtectedRoute";
import {Route, Routes} from "react-router";
import {BrowserRouter} from "react-router-dom";
import Login from "./pages/Login";
import Home from "./pages/Home";
import ScrollToTop from "./ScrollToTop";


const App = () => {

    return (
       <MainRoutes/>
    );

}

export default App;
