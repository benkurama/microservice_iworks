import axios from "axios";
import authHeader from "./AuthHeader";
const API_URL = "http://localhost:1103/resource";
const getResourceMenu = () => {
    return axios.post(API_URL + "/resMenu",{ headers: authHeader() });
};

const UserResource = {
    getResourceMenu
};
export default UserResource;