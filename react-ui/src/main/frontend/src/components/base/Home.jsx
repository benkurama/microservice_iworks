import React, {useState, useEffect} from "react";
import UserResource from "../../services/UserResource";
import Header from "./Header";
import AuthService from "../../services/AuthService";
const Home = () => {
    const [content, setContent] = useState("");
    const user = AuthService.getCurrentUser();

    useEffect(() => {
        UserResource.getResourceMenu().then(
            (response) => {
                setContent(response.data);
            },
            (error) => {
                const _content =
                    (error.response && error.response.data) ||
                    error.message ||
                    error.toString();
                setContent(_content);
            }
        );
    }, []);
    return (
        <>
            <div className="container">
                <header className="jumbotron">
                    <h3>HELLO, token: {user.token}</h3>
                </header>
            </div>
        </>
    );
};
export default Home;
