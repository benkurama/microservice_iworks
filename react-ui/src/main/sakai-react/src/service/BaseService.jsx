import axios from "axios";
import authHeader from "./AuthHeader";
const API_URL = "http://localhost:8080/";

const HttpGet = (path) => {
    return axios.get(API_URL + path,{ headers: authHeader() });
};

const BaseService = {
    HttpGet
};
export default BaseService;