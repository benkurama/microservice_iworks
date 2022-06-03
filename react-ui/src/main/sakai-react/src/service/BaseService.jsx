import axios from "axios";
//import authHeader from "./AuthHeader";
const API_URL = "http://localhost:8080/";

const HttpGet = (path) => {
    return axios.get(API_URL + path);
};

const HttpGet002 = (host, path) => {

    //axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
    return axios.get(host + path);
};

const BaseService = {
    HttpGet,
    HttpGet002
};
export default BaseService;