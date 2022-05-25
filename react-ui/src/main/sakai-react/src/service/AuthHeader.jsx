import AuthService from "./AuthService";

export default function authHeader() {

    const user = JSON.parse(localStorage.getItem('user'));
    if (user && user.token) {
       // return { Authorization: AuthService.setupAxiosInterceptors(user.token) }
        console.log("done 2");
        //return { Authorization: 'Bearer ' + user.token };
    } else {
        return {};
    }
}
