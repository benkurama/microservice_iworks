import {Outlet} from 'react-router-dom'
import React from "react";

const  Content=() =>{
    return <div className='inner-content'>
        <Outlet/>
    </div>;
};

export default Content;