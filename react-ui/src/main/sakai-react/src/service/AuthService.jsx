
import axios from  '../components/base/axios';

const API_URL = "http://localhost:8090";
const  login = async (username,password) => {
        console.log(username);
        return await axios.post(API_URL + "/auth/login", {
            username,
            password,
            }
        );
    /*return axios.post(API_URL + "login", {
>>>>>>> dev-angel
        username,
        password
    })  ;*/

};
const createJWTToken = (token) =>{
    return 'Bearer ' + token
};
const logout   = () => {
    localStorage.removeItem("user");
    localStorage.removeItem("token");
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

    localStorage.setItem('token', token );

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
