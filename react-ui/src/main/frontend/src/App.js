import logo from './logo.svg';
import './App.css';
import "bootstrap/dist/css/bootstrap.min.css";
import Login from "./components/Login";
import React, {useState, useEffect, useCallback} from "react";
import {Routes, Route, Link, BrowserRouter} from "react-router-dom";
import AuthService from "./services/AuthService";
import Home from "./components/base/Home";
import Profile from "./components/Profile";
import EventBus from "./common/EventBus";
import AuthenticatedRoute from "./components/AuthenticatedRoute";
import Header from "./components/base/Header";
import MainRoutes from "./components/base/MainRoutes";

function App() {
  const [currentUser, setCurrentUser] = useState(undefined);
  //const dispatch = useDispatch();
  useEffect(() => {
    const user = AuthService.getCurrentUser();
    if (user) {
      setCurrentUser(user);
    }
 /*   const logOut = useCallback(() => {
      dispatch(logout());
    }, [dispatch]);

    EventBus.on("logout", () => {
      logOut();
    });
*/
    window.addEventListener('storage', AuthService.getCurrentUser);
    return () => {
      EventBus.remove("logout");
      window.removeEventListener('storage', AuthService.getCurrentUser)
    };
  }, []);


  return (
      <div className="app">
            <Header/>
            {/*<Sidebar/>*/}
            <MainRoutes/>
             {/* <Routes>
                <Route path="/" element={<Login/>} />
                <Route path="/home"
                       element={<AuthenticatedRoute/>}>
                  <Route path="/home" element={<Home/>}/>
                </Route>
                <Route path="/profile"
                       element={<AuthenticatedRoute/>}>
                  <Route path="/profile" element={<Profile/>}/>
                </Route>
            </Routes>*/}
    </div>
  );
};
export default App;
