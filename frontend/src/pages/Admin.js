import React, { useState, useEffect } from 'react';
import CreateDataSet from "./CreateDataSet"
import Header from '../Components/Header';
import './css/Admin.css';
import SideBar from '../Components/SideBar';

const Admin = () => {

    const [members, setMembers] = useState([]);

    useEffect(() => {
        fetchMembers();
    }, []);

    const fetchMembers = async () => {
        try {
            const response = await fetch('/showUserInfoList');
            const data = await response.json();
            setMembers(data);
        } catch (error) {
            console.error('Error', error);
        }
    }


    return (
        <div className="fade-in">
            <Header signout />
            {/* <MenuBar /> */}
            <SideBar />
            <div className="container">
                <h1>회원 목록</h1>
                <ul>
                    {members.map((member) => (
                        <li key={member.id}>{member.name}</li> 
                    ))}
                </ul>
            </div>
        </div>
    )
}

export default Admin;