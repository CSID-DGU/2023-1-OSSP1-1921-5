import React from 'react';
import { Link } from 'react-router-dom';
import Box from '@mui/material/Box'
import Stack from '@mui/material/Stack'
import AccountCircleRoundedIcon from '@mui/icons-material/AccountCircleRounded';
import LogoutOutlinedIcon from '@mui/icons-material/LogoutOutlined';
import "./Header.css";

const Header = ({ mypage, signout }) => {
    const onClickLogout = () => { 
        sessionStorage.clear() 
        window.location.replace('/') 
    }

    return (
        <Stack className="nav" direction="row" justifyContent="space-between" alignItems="center">
            <Box className="tool" title="로그아웃">
                {signout &&
                    <Link to="/">
                        <Stack direction="row" onClick={onClickLogout}>
                            <LogoutOutlinedIcon /><div className="tool_title">로그아웃</div>
                        </Stack>
                    </Link>
                }
            </Box>
            <Link to='/'>
                <Stack className="to_home" direction="row" title="홈으로">
                    <img className="to_home_img" alt="YouCanGraduate" src="img/logo.png"></img>
                    <span className="to_home_title">졸업할 수 있을까?</span>
                </Stack>
            </Link>
            <Box className="tool" title="마이페이지">
                {mypage &&
                    <Link to="/mypage">
                        <Stack direction="row">
                            <AccountCircleRoundedIcon /><div className="tool_title">마이페이지</div>
                        </Stack>
                    </Link>
                }
            </Box>
        </Stack>
    )
}

export default Header;