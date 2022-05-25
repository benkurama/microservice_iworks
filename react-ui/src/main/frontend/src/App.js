
import './App.css';
import "bootstrap/dist/css/bootstrap.min.css";

import React, {useState, useEffect} from "react";

import AuthService from "./services/AuthService";

import EventBus from "./common/EventBus";

import MainRoutes from "./components/base/MainRoutes";


function App() {
  const [currentUser, setCurrentUser] = useState(undefined);

  useEffect(() => {
    const user = AuthService.getCurrentUser();
    if (user) {
      setCurrentUser(user);
    }

    window.addEventListener('storage', AuthService.getCurrentUser);
    return () => {
      EventBus.remove("logout");
      window.removeEventListener('storage', AuthService.getCurrentUser)
    };
  }, []);


  return (
      <div className="app">
            
            <MainRoutes/>

    </div>
  );
};
export default App;
