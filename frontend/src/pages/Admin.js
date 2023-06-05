import React from 'react';
import MenuBar from '../Components/MenuBar';
import { NavLink as RouterLink, Route, Routes } from 'react-router-dom';
import CreateDataSet from "./CreateDataSet"
import Header from '../Components/Header';
import './css/Admin.css';

const Admin = () => {
    return (
        <div className="fade-in">
            <Header signout />
            <MenuBar /> 
            <Routes>
                <Route path="/admin" exact={true} />
                <Route path="/change" element={<CreateDataSet />} />
                <Route path="/newsem" element={<CreateDataSet />} />
                <Route path="/dataset" element={<CreateDataSet />} />
            </Routes>
        </div>
    )
}

export default Admin;