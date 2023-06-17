import React, { useState, useEffect } from 'react';
import SideBar from '../Components/SideBar';
import { List, ListItem } from '@material-ui/core';
import Box from "@mui/material/Box";
import {Link} from "react-router-dom";
import Stack from "@mui/material/Stack";
import LogoutOutlinedIcon from "@mui/icons-material/LogoutOutlined";

const Admin = () => {

    const [members, setMembers] = useState([]);
    const [memberInfo, setMemberInfo] = useState(''); // 검색
    const [searchResult, setSearchResult] = useState(null); // 검색 결과

    const onClickLogout = () => {
        sessionStorage.clear()
        window.location.replace('/')
    }

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
                body: JSON.stringify( searchInfo ),
            });

            if (res.ok) {
                const member = await res.json();
                setSearchResult(member);
            } else {
                alert("존재하지 않는 회원입니다.")
                window.location.href = "/admin"
                console.error('Error1 : ', res.status);
                console.error(res);
            }
        } catch (error) {
            console.error('Error : ', error);
        }
    }

    const handleDeleteProfile = (email) => {

        if (window.confirm('회원을 탈퇴시키시겠습니까?')) {
            fetch("/forcedeletion", {
                method: 'post',
                headers: {
                    "content-type": "application/json",
                },
                body: JSON.stringify( email ),
            })
            .then(() => {
                alert('탈퇴 완료');
                window.location.replace('/admin');
            })
        } else {
            return;
        }
    };

    return (
        <div className="fade-in">
            <Box className="tool" title="로그아웃">
                <Link to="/">
                    <Stack direction="row" onClick={onClickLogout}>
                        <LogoutOutlinedIcon /><div className="tool_title">로그아웃</div>
                    </Stack>
                </Link>
            </Box>
            <SideBar />
            <div className="mainBox">
            <Box className="sub_title">회원 목록</Box>
            <Box className="guide">
            <div style={{fontSize:'20px',fontFamily:'SsangMun',marginBottom:'15px'}}>[ 회원 검색 ]</div>
                <form onSubmit={handleSubmit}>
                    <input
                        className="textfield"
                        type="text"
                        value={memberInfo}
                        onChange={(e) => setMemberInfo(e.target.value)}
                        placeholder="회원 아이디 입력"
                        variant="outlined"
                    />

                    <button className="btn" type="submit" variant="contained">검색</button>
                </form>
                {searchResult && (
                <List>
                    <ListItem>Email: {searchResult.email}</ListItem>
                    <ListItem>Password: {searchResult.pw}</ListItem>
                    <ListItem>Student Number: {searchResult.studentNumber}</ListItem>
                    <ListItem>Semester: {searchResult.semester}</ListItem>
                    <ListItem>
                        <button
                            onClick={() => handleDeleteProfile(searchResult.email)}
                            style={{ marginLeft: '10px' }}
                        >탈퇴시키기</button>
                    </ListItem>
                </List>
                )}
                <ul>
                    {members.map((member) => {
                        if (member.email === "1921@dgu.ac.kr") {
                            return null;
                        }

                        return (
                            <li key={member.email}>
                                {member.email}, {member.pw}, {member.studentNumber}, {member.semester}
                                <button
                                    onClick={() => handleDeleteProfile(member.email)}
                                    style={{ marginLeft: '10px' }}
                                >
                                    탈퇴시키기
                                </button>
                            </li>
                        );
                    })}
                </ul>
                </Box>
            </div>
        </div>
    )
}

export default Admin;