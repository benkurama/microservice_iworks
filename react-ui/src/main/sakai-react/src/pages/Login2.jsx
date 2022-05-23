import '../assets/css/loginStyle.css';
import React, {useState, useRef} from "react";

import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import AuthService from "../service/AuthService";
import { useNavigate} from 'react-router-dom';
import CheckButton from "react-validation/build/button";


const h2Style = {

        "marginBottom": "5px",
        "textAlign": "center",
        "textShadow": "0 4px 16px #fff",
        "fontSize": "30px",
        "fontWeight": "100"
      
}

const fieldsetStyle = {
    "margin": "0",
    "backgroundColor": "#fff",
    "border": "none",
    "borderRadius": "5px",
    "boxShadow": "0 1px 3px rgba(0,0,0,0.2)",
}

const legendStyle = {
    "padding": "5px",
            "backgroundColor": "#fff",
            "borderRadius": "5px"
}

const ulStyle = {
    "margin": "0",
    "padding": "0",
    "listStyleType": "none",
}

const liStyle = {
    "display": "grid",
    "alignItems": "center",
    "margin": "10px"
}

const lableStyle = {
    "textAlign": "left",
    "paddingBottom": "2px"
}

const inputStyle = {
    "width": "100%",
    "padding": "5px",
    "border": "1px solid #ddd",
    "borderRadius": "5px",
    "&:hover": {
        "border": "1px solid #aaf"
      }
}

const buttonStyle = {
    "padding": "10px",
    "border": "1px solid rgba(0,0,0,0)",
    "borderRadius": "5px",
    "background": "#fff",
    "boxShadow": "0 1px 3px rgba(0,0,0,0.2)",
    "&:hover": {
        "backgroundColor": "#eef",
        "border": "1px solid #aaf"
      }
}

const formStyle = {
    "gridColumn": "2",
    "gridRow": "2",
    "display": "grid",
    "gridGap": "10px",
    "margin": "auto 0",
    "padding": "35px",
    "backgroundColor": "rgba(255,255,255,0.9)",
    "borderRadius": "10px",
    "boxShadow": "0 32px 64px rgba(0,0,0,0.2)",
  };

  const Login = () => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const form = useRef();
    const checkBtn = useRef();
    const [message, setMessage] = useState("");
    const navigate = useNavigate();

    const onChangeUsername = (e) => {
        const username = e.target.value;
        setUsername(username);
    };

    const onChangePassword = (e) => {
        const password = e.target.value;
        setPassword(password);
    };

    const required = (value) => {
        if (!value) {
            return (
                <div className="alert alert-danger" role="alert">
                    This field is required!
                </div>
            );
        }
    };

    const handleLogin = (e) => {
        e.preventDefault();
        setMessage("");
        setLoading(true);
        form.current.validateAll();
        /*if (checkBtn.current.context._errors.length === 0) {*/
        AuthService.login(username, password)
            .then((response)=> {
                console.log(response);
                    if (response.data.token){
                        localStorage.setItem("user",JSON.stringify(response.data));
                        AuthService.setupAxiosInterceptors(AuthService.createJWTToken(response.data.token));
                        navigate("/");
                    }
            //return response.data;
            }).catch((error) => {
                const resMessage =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();
                setLoading(false);
                setMessage(resMessage);
            });
        /*} else {
            setLoading(false);
        }*/
    };

    return (
        <section id="entry-page">
            <Form style={formStyle} onSubmit={handleLogin} ref={form}>
                <h2 style={h2Style}>Welcome Back!</h2>
                <fieldset style={fieldsetStyle}>
                    <legend style={legendStyle}>Log In</legend>
                    <ul style={ulStyle}>
                        <li style={liStyle}>
                            <label htmlFor="username" style={lableStyle}>Username:</label>
                            <Input
                                type="text"
                                className="form-control"
                                name="username"
                                value={username}
                                onChange={onChangeUsername}
                                validations={[required]}
                                style={inputStyle}
                            />
                        </li>

                        <li style={liStyle}>
                            <label htmlFor="password" style={lableStyle}>Password:</label>
                            <Input
                                type="password"
                                className="form-control"
                                name="password"
                                value={password}
                                onChange={onChangePassword}
                                validations={[required]}
                                style={inputStyle}
                            />
                        </li>
                        <li style={liStyle}>
                            <i />
                            <a onClick={() => this.changeView("")} href="#">Create Account?</a>
                        </li>
                    </ul>
                </fieldset>
                <button style={buttonStyle} disabled={loading}>Login</button>

                <div>
                    {message && (
                        <div className="form-group">
                            <div style={{ backgroundColor: "#fdc0b4", padding: '15px' }}>
                                {message}
                            </div>
                        </div>
                    )}
                    {loading && (
                        "Connecting..."
                    )}
                </div>
            </Form>
        </section>
    );

};

export default Login;