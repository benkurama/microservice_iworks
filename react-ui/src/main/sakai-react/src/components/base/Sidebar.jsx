 // eslint-disable-next-line
export default function Sidebar() {

    return(

        
            <div className="col-md-3" style={{backgroundColor: "#fafafa", borderRight: "#e1e1e1 1px solid"}}>
                <ul className="list-group">
                    <li className="list-group-item">Profile</li>
                    <li className="list-group-item">
                        <ul>
                            <div>
                                
                                <a href="#" id="accountsclick">Accounts</a>

                            </div>
                        </ul>
                    </li>
                    <li className="list-group-item">
                        <ul>
                            <div>
                                <a href="#" id="orgzclick">Organization</a>

                            </div>
                        </ul>
                    </li>
                    <li className="list-group-item">
                        <ul>
                            <div>
                                <a href="#" id="rolesclick">Roles</a>

                            </div>
                        </ul>
                    </li>

                    <li className="list-group-item">Work Order</li>
                    <li className="list-group-item">
                        <ul>
                            <li>
                                <div>
                                    <a href="#" id="installorderclick">Install Order</a>

                                </div>

                            </li>
                        </ul>
                    </li>

                    <li className="list-group-item">Billing</li>
                    <li className="list-group-item">
                        <ul>
                            <li>Invoice</li>
                        </ul>
                    </li>
                    <li className="list-group-item">
                        <ul>
                            <li>Collection</li>
                        </ul>
                    </li>
                    <li className="list-group-item">
                        <ul>
                            <li>Returned</li>
                        </ul>
                    </li>
                    <li className="list-group-item">
                        <ul>
                            <li>Replied</li>
                        </ul>
                    </li>

                    <li className="list-group-item">Human Resources</li>
                    <li className="list-group-item">
                        <ul>
                            <li>Attendance Import</li>
                        </ul>
                    </li>
                    <li className="list-group-item">
                        <ul>
                            <li>Work Report</li>
                        </ul>
                    </li>

                </ul>
            </div>
       
    )
}