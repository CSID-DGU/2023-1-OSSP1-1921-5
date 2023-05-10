import React from 'react';
import { Link } from 'react-router-dom';
import Box from '@mui/material/Box'
import Stack from '@mui/material/Stack';
import LogoutOutlinedIcon from '@mui/icons-material/LogoutOutlined';
import AccountCircleRoundedIcon from '@mui/icons-material/AccountCircleRounded';
import "./css/Home.css";
import { useState, useEffect } from 'react';
import LoadingSpinner from '../Components/LoadingSpinner';

const Home = () => {
    const [loading, setLoading] = useState(true);
    const [isSignIn, setIsSignIn] = useState(false)
    const onClickLogout = () => {
        sessionStorage.clear()
        window.location.replace('/')
    }

    useEffect(() => {
        setLoading(false)
        console.log(sessionStorage.getItem('userId'))
        if (sessionStorage.getItem('userId') === null) {
            // 로그인 안 되었다면
            setIsSignIn(false)
        }
        else {
            // 로그인 되었다면
            setIsSignIn(true)
        }
    }, [])


    return (
        <>
            {loading ? <LoadingSpinner op={true} /> : (
                <>
                <div className="home_box">
                    <div className="title_area">
                        <div className="logo">
                            <img className="logo_img" alt="YouCanGraduate" src="img/logo.png"></img>
                        </div>
                        <span className="title">졸업할 수 있을까?</span>
                        <div className="link">
                            <a href="https://github.com/CSID-DGU/2022-1-OSSP2-turning-7">github.com/🎓</a>
                        </div>
                    </div>
                    <Stack className="btn_area" spacing={1}>
                        {!isSignIn &&
                            <Link to='/signin'>
                                <button className="btn">로그인</button>
                            </Link>
                        }
                        {isSignIn &&
                            <>
                                <Link to='/input'>
                                    <button className="btn">입력</button>
                                </Link>
                                <Link to='/result'>
                                    <button className="btn">결과</button>
                                </Link>
                                <Link to='/stats'>
                                    <button className="btn">통계</button>
                                </Link>
                            </>
                        }
                    </Stack>
                </div>
                {isSignIn &&
                    <>
                    <Box className="signout_home" title="로그아웃">
                        <Link to="/" style={{ color: 'black', textDecoration: 'none' }}>
                            <Stack direction="row" onClick={onClickLogout}>
                                <LogoutOutlinedIcon /><div className="tool_title">로그아웃</div>
                            </Stack>
                        </Link>
                    </Box>
                    <Box className="mypage_home" title="마이페이지">
                        <Link to="/mypage" style={{ color: 'black', textDecoration: 'none' }}>
                            <Stack direction="row">
                                <AccountCircleRoundedIcon /><div className="tool_title">마이페이지</div>
                            </Stack>
                        </Link>
                    </Box>
                    </>
                }
                </>
            )}
        </>
    );
};

export default Home;