
import { Link } from 'react-router-dom';
import classNames from 'classnames';
import React, {useEffect, useState} from "react";
import fiberlog from './assets/images/fiberlog.png';

export const AppTopbar = (props) => {

    const [currentUser, setCurrentUser] = useState("user");
    const [role, setRole] = useState("");

    useEffect(() => {
        const user = JSON.parse(localStorage.getItem("user"));
        if (user) {
            setCurrentUser(user)
            setRole(user.roles[0].substring(5, user.roles[0].length));
        }

    }, []);

    return (
        <div className="layout-topbar">
            <Link to="/" className="layout-topbar-logo" >
                <img src={fiberlog} alt="logo" />
                <span>Inventory System</span>
            </Link>

            <button type="button" className="p-link  layout-menu-button layout-topbar-button" onClick={props.onToggleMenuClick}>
                <i className="pi pi-bars"/>
            </button>

            <button type="button" className="p-link layout-topbar-menu-button layout-topbar-button" onClick={props.onMobileTopbarMenuClick}>
                <i className="pi pi-ellipsis-v" />
            </button>

                <ul className={classNames("layout-topbar-menu lg:flex origin-top", {'layout-topbar-menu-mobile-active': props.mobileTopbarMenuActive })}>

                    <li>
                        <button className="p-link layout-topbar-button" >
                                {currentUser.username + ':'}
                        </button>
                    </li>

                    <li>
                        <div style={{position:'relative', top:'13px', padding: '0px 0px 0px 30px'}}>
                                {role}
                        </div>
                    </li>

                    <li>
                        <button className="p-link layout-topbar-button" onClick={props.onMobileSubTopbarMenuClick}>
                            <i className="pi pi-calendar"/>
                            <span>Events</span>
                        </button>
                    </li>
                    <li>
                        <button className="p-link layout-topbar-button" onClick={props.onMobileSubTopbarMenuClick}>
                            <i className="pi pi-cog"/>
                            <span>Settings</span>
                        </button>
                    </li>
                    <li>
                        <button className="p-link layout-topbar-button" onClick={props.onMobileSubTopbarMenuClick}>
                            <i className="pi pi-user"/>
                            <span>Profile</span>
                        </button>
                    </li>
                    <li>
                        <button className="p-link layout-topbar-button" onClick={props.onMobileSubTopbarLOGOUT}>
                            <i className="pi pi-sign-out"/>
                            <span>Logout</span>
                        </button>
                    </li>
                    <li>

                    </li>
                </ul>
        </div>
    );
}
