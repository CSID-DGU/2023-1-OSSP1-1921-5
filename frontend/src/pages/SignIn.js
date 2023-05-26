import React from 'react';
import { Link } from 'react-router-dom';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box'
import "./css/UserInfo.css";
import FindPW from '../Components/FindPW';
import Header from '../Components/Header';

const SignIn = () => {
    const [id, setId] = React.useState('')
    const [emptyId, setEmptyId] = React.useState(false)
    const [pw, setPw] = React.useState('')
    const [emptyPw, setEmptyPw] = React.useState(false)

    const onClickSignIn = (e) => {
        if (id === '') { setEmptyId(true) }
        else { setEmptyId(false) }
        if (pw === '') { setEmptyPw(true) }
        else { setEmptyPw(false) }

        e.preventDefault()
        const data = {
            email: id,
            pw: pw,
        }
        console.log(data);

        fetch("/signin", {
            method: 'post',
            headers: {
                "content-type": "application/json",
            },
            body: JSON.stringify(data)
        })
            .then((res) => res.json())
            .then((json) => {
                console.log('json', json)

                if (json.id === undefined) {
                    // id 일치하지 않는 경우 userId = undefined, msg = '입력하신 id 가 일치하지 않습니다.'
                    console.log('없는 id')
                    alert('없는 ID입니다.')
                } else if (json.id === null) {
                    // id는 있지만, pw 는 다른 경우 userId = null , msg = undefined
                    console.log('잘못된 비밀번호')
                    alert('잘못된 비밀번호입니다.')
                } else if (json.id === id) {
                    // id, pw 모두 일치 userId = userId1, msg = undefined
                    console.log('로그인 성공')
                    sessionStorage.setItem('userId', id)
                    console.log(sessionStorage.getItem('userId'))
                    // 작업 완료 되면 페이지 이동(새로고침)
                    document.location.href = '/'
                }

            })
            .catch()
    }

    const onChangeId = (e) => {
        setId(e.target.value)
    }

    const onChangePw = (e) => {
        setPw(e.target.value)
    }

    const onClickSignUp = () => {
        console.log('회원가입 버튼 클릭')
    }

    return (
        <div className="fade-in">
            <Header />
            <Box className="signin">
                로그인
            </Box>
            <Box className="text_area_in" component="form">
                <TextField
                    className="text_in"
                    error={emptyId}
                    name='id'
                    label='이메일'
                    variant="outlined"
                    margin="normal"
                    onChange={onChangeId} />
                <span className="helper">{emptyId && '이메일을 입력하세요.'}</span>
                <TextField
                    className="text_in"
                    error={emptyPw}
                    name='pw'
                    label='비밀번호'
                    type='password'
                    variant="outlined"
                    margin="normal"
                    onChange={onChangePw} />
                <span className="helper">{emptyPw && '비밀번호를 입력하세요.'}</span>
            </Box>
            <Box className="btn_area" style={{ marginBottom: 0 }}>
                <button className="btn" onClick={onClickSignIn}>로그인</button>
            </Box>
            <Box className="sub_btn_area">
                <Link to='/signup'>
                    <button className="sub_btn" onClick={onClickSignUp}>회원가입</button>
                </Link>
                <FindPW>비밀번호찾기</FindPW>
            </Box>
        </div>
    );
};

export default SignIn;