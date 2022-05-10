import axios from "axios";
import authHeader from "./AuthHeader";
const API_URL = "http://localhost:8070/resource";
const getResourceMenu = () => {
    return axios.get(API_URL + "/resMenu",{ headers: authHeader() });
};

const UserResource = {
    getResourceMenu
};
export default UserResource;