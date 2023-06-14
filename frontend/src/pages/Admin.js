import React, { useState, useEffect } from 'react';
import Header from '../Components/Header';
import './css/Admin.css';
import SideBar from '../Components/SideBar';
import { TextField, Button, List, ListItem } from '@material-ui/core';

const Admin = () => {

    const [members, setMembers] = useState([]);
    const [memberInfo, setMemberInfo] = useState(''); // 검색
    const [searchResult, setSearchResult] = useState(null); // 검색 결과

    useEffect(() => {
        const fetchMembers = async (e) => {
            try {
                const response = await fetch('/showUserInfoList', {
                    method: 'POST',
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
        console.log(searchInfo);
        console.log(memberInfo);
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
                setSearchResult(member);
            }
            else {
                alert("존재하지 않는 회원입니다.")
                //window.location.href = "/admin"
                console.error('Error1 : ', res.status);
                console.error(res);
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
                {searchResult && (
                <List>
                    <ListItem>Email: {searchResult.email}</ListItem>
                    <ListItem>Password: {searchResult.pw}</ListItem>
                    <ListItem>Student Number: {searchResult.studentNumber}</ListItem>
                    <ListItem>Semester: {searchResult.semester}</ListItem>
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