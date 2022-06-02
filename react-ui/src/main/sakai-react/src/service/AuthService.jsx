import axios from  '../components/base/axios';

const API_URL = "http://localhost:8090/auth/";
const  login = async (username,password) => {
        console.log(username);
        return await axios.post("/auth/login", {
            username,
            password,
            },
            {
                headers: { 'Content-Type': 'multipart/form-data'},
                withCredentials: true
            }
        );
    /*return axios.post(API_URL + "login", {
        username,
        password
    })  ;*/

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
    /* axios.interceptors.request.use(
        (config) => {
            if (isUserLoggedIn()) {
                config.headers.authorization = token
                //config.headers["Authorization"] = token
            }
            return config
        }
    ) */
    if(isUserLoggedIn()){
        axios.defaults.headers['Authorization'] = token;
    }else{
        axios.defaults.headers['Authorization'] = "";
    }
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
