import React, { useState, useEffect } from 'react';
import Header from '../Components/Header';
import './css/Admin.css';
import SideBar from '../Components/SideBar';
import { TextField, Button, List, ListItem } from '@material-ui/core';

const Admin = () => {

    const [members, setMembers] = useState([]);
    const [memberInfo, setMemberInfo] = useState('');
    const [searchMember, setSearchMember] = useState(null);

    useEffect(() => {
        const fetchMembers = async (e) => {
            try {
                const response = await fetch('/showUserInfoList', {
                    method: 'POST',  // POST 요청으로 변경
                    headers: {
                        'Content-Type': 'application/json',
                    },
                });
                const data = await response.json();
                setMembers(data.userInfoDTOS);
            } catch (error) {
                console.error('Error', error);
            }
        }
        fetchMembers();
    }, []);


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
                alert("존재하지 않는 회원입니다.")
                window.location.href = "/admin"
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
                {searchMember && (
                <List>
                    <ListItem>Email: {searchMember.email}</ListItem>
                    <ListItem>Password: {searchMember.pw}</ListItem>
                    <ListItem>Student Number: {searchMember.studentNumber}</ListItem>
                    <ListItem>Semester: {searchMember.semester}</ListItem>
                </List>
                )}
                <ul>
                    {members.map((member) => (
                        <li key={member.email}>{member.email}, {member.pw}, {member.studentNumber}, {member.semester}</li>
                    ))}
                </ul>
            </div>
        </div>
    )
}

export default Admin;