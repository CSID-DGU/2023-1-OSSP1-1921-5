import React, { useState, useEffect } from 'react';
import Box from '@mui/material/Box'
import Stack from '@mui/material/Stack'
import './css/Input.css';
import Header from '../Components/Header';
import UploadFile from '../Components/UploadFile';
import AlertModal from '../Components/AlertModal';
import LoadingSpinner from '../Components/LoadingSpinner';

const Input = () => {
    const [hasInput, setHasInput] = useState();
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const data = {
            email: sessionStorage.getItem('userId')
        }
        fetch("/input", {
            method: 'post',
            headers: {
                "content-type": "application/json",
            },
            body: JSON.stringify(data)
        })
        .then((res) => res.json())
        .then((json) => {
            console.log(json)
            setLoading(false)
            setHasInput(json.result)
        })
    }, [])

    return (
        <>
        {loading ? <LoadingSpinner op={true} /> : (
            <div className="fade-in">
                {hasInput && 
                    <AlertModal 
                        msg1="저장된 결과가 있습니다."
                        msg2="새로 입력하실건가요?"
                        move1="/result"
                        move2="/input"
                        op1="이전 결과 보기"
                        op2="새로 입력"
                        />
                }
                <Header mypage signout />
                <Box className="sub_title">
                    파일업로드
                </Box>
                <Box className="guide">
                    <Stack>
                        <span style={{fontSize:'20px',fontFamily:'SsangMun',marginBottom:'15px'}}>[ 파일 다운로드 방법 ]</span>
                        <span style={{fontSize:'16px',margin:'0 30px 5px 30px'}}>mdrims 로그인 → 학사정보 → 성적 → 전체성적 조회 → 엑셀 아이콘 클릭</span>
                        <span style={{fontSize:'13px'}}>(mdrims 바로가기:
                            <a href="https://mdrims.dongguk.edu/" style={{color:'#007FFF'}}> https://mdrims.dongguk.edu/</a>)
                        </span>
                    </Stack>
                    <img className="guide_img" alt="엑셀 파일 다운로드 방법" src="img/guide1.png"></img>
                    <img className="guide_img" alt="엑셀 파일 다운로드 방법" src="img/guide2.png"></img>
                </Box>
                <UploadFile />    
            </div>
        )}
        </>
    );
};

export default Input;