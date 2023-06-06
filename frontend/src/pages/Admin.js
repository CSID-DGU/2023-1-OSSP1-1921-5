import React from 'react';
import { NavLink as RouterLink, Route, Routes } from 'react-router-dom';
import CreateDataSet from "./CreateDataSet"
import Header from '../Components/Header';
import './css/Admin.css';
import SideBar from '../Components/SideBar';

const Admin = () => {
    return (
        <div className="fade-in">
        <Header signout />
        {/* <MenuBar /> */}
        <SideBar />
        <div className="container">
            <Routes>
                <Route path="/admin" exact={true} />
                <Route path="/change" element={<CreateDataSet />} />
                <Route path="/newsem" element={<CreateDataSet />} />
                <Route path="/dataset" element={<CreateDataSet />} />
            </Routes>
        </div>
        </div>
    )
}

export default Admin;