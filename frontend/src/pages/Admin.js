import React, { useState, useEffect } from 'react';
import Header from '../Components/Header';
import './css/Admin.css';
import SideBar from '../Components/SideBar';
import { TextField } from '@material-ui/core';

const Admin = () => {

    const [members, setMembers] = useState([]);
    const [memberInfo, setMemberInfo] = useState('');
    const [searchMember, setSearchMember] = useState(null);

    useEffect(() => {
        fetchMembers();
    }, []);

    const fetchMembers = async (e) => {
        try {
            const response = await fetch('/showUserInfoList');
            const data = await response.json();
            setMembers(data.userInfoDTOS);
        } catch (error) {
            console.error('Error', error);
        }
    }

    const handleSubmit = async (e) => {
        e.preventDefault();

        const searchInfo = {
            email : memberInfo
        }

        try {
            const res = await fetch('/showUserInfoById', {
                method: 'POST',
                headers: {
                    'content-type' : 'application/json',
                },
                body: JSON.stringify({ searchInfo }),
            });

            if (res.ok) {
                const member = await res.json();
                setSearchMember(member);
            }
            else {
                console.error('Error : ', res.status);
            }
        } catch (error) {
            console.error('Error : ', error);
        }
    }

    return (
        <div className="fade-in">
            <Header signout />
            <SideBar />
            <div className="container">
                <h1>회원 목록</h1>
                <form onSubmit={handleSubmit}>
                    <TextField
                        className="input"
                        type="text"
                        value={memberInfo}
                        onChange={(e) => setMemberInfo(e.target.value)}
                        label="회원 아이디 입력"
                        variant="outlined"
                    />
                    <Button type="submit" variant="contained">조회</Button>
                </form>
                {memberInfo && (
                <List>
                    <ListItem className={classes.listItem}>Email: {searchMember.email}</ListItem>
                    <ListItem className={classes.listItem}>Password: {searchMember.pw}</ListItem>
                    <ListItem className={classes.listItem}>Student Number: {searchMember.studentNumber}</ListItem>
                    <ListItem className={classes.listItem}>Semester: {searchMember.semester}</ListItem>
                </List>
                )}
                <ul>
                    {members.map((member) => (
                        <li key={member.email}>{member.emial}, {member.pw}, {member.studentNumber}, {member.semester}</li> 
                    ))}
                </ul>
            </div>
        </div>
    )
}

export default Admin;