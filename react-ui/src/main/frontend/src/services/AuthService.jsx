import axios from "axios";
const API_URL = "http://localhost:9999/auth/";
const  login = (username,password) => {
    console.log(username);
    return axios.post(API_URL + "login", {
        username,
        password
    })  ;

};
const createJWTToken = (token) =>{
    return 'Bearer ' + token
};
const logout   = () => {
    localStorage.removeItem("user");
};

const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("user"));
};

const isUserLoggedIn = () => {
    let user = localStorage.getItem("user");
    return user !== null;

};

const setupAxiosInterceptors = (token) => {
    axios.interceptors.request.use(
        (config) => {
            if (isUserLoggedIn()) {
                config.headers.authorization = token
            }
            return config
        }
    )
};

const AuthService = {
    login,
    logout,
    createJWTToken,
    getCurrentUser,
    isUserLoggedIn,
    setupAxiosInterceptors
};
export default AuthService;