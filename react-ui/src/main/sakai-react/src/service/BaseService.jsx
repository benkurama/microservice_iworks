
import axios from  'axios';

//import authHeader from "./AuthHeader";
const API_URL = "http://localhost:8090/";


const HttpGet = (path) => {
    return axios.get(API_URL + path);
};

const HttpGet002 = (host, path) => {

    //axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
    return axios.get(host + path);
};

// benkuramax added

let toks = localStorage.getItem('token');
console.log(toks);

axios.interceptors.request.use((config) => {
    //console.log('axios.interceptors.request');
    config.headers['Authorization'] = toks;
    return config;
}, (error) => {
    console.log(JSON.stringify(error));
    return Promise.reject(error);
});

const BaseService = {
    HttpGet,
    HttpGet002
};
export default BaseService;
