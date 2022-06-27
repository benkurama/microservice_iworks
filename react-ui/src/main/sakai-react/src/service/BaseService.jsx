
import axios from  'axios';
import AuthService from "../service/AuthService";
//import authHeader from "./AuthHeader";
const API_URL = "http://localhost:8090/";



const HttpGet = (path) => {
    
    getAuth();
    var res = axios.get(API_URL + path);
    return res;
};

const HttpGet002 = (host, path) => {
    getAuth();
    return axios.get(host + path);
};

const getAuth = () => {
    const user = AuthService.getCurrentUser();
    axios.defaults.headers['Authorization'] = 'Bearer '+ user.id;
}

// benkuramax added

//let toks = localStorage.getItem('token');
/*const user = AuthService.getCurrentUser();
debugger;
if(user !== null){

    console.log(user.id);
    axios.interceptors.request.use((config) => {
        //console.log('axios.interceptors.request');
        config.headers['Authorization'] = user.id;
        return config;
    }, (error) => {
        console.log(JSON.stringify(error));
        return Promise.reject(error);
    });
} */


const BaseService = {
    HttpGet,
    HttpGet002
};
export default BaseService;
