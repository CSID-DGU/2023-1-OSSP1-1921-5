import React from 'react';
import Select from '@mui/material/Select'
import MenuItem from '@mui/material/MenuItem'
import FormControl from '@mui/material/FormControl'
import InputLabel from '@mui/material/InputLabel'
import TextField from '@mui/material/TextField'
import Box from '@mui/material/Box'
import Stack from '@mui/material/Stack'
import CheckIcon from '@mui/icons-material/Check';
import "./css/UserInfo.css";
import Header from '../Components/Header';
import ConversionTable from '../Components/ConversionTable';

const SignUp = () => {
    const [email, setEmail] = React.useState('')
    const [emptyEmail, setEmptyEmail] = React.useState(false)
    const [duplicatedEmail, setDuplicatedEmail] = React.useState(true)
    const [emailAddress, setEmailAddress] = React.useState('')
    const [emptyEmailAddress, setEmptyEmailAddress] = React.useState(false)
    const [password, setPassword] = React.useState('')
    const [emptyPW, setEmptyPW] = React.useState(false)
    const [year, setYear] = React.useState('')
    const [emptyYear, setEmptyYear] = React.useState(false)
    const [semester, setSemester] = React.useState('')
    const [emptySemester, setEmptySemester] = React.useState(false)
    const [course, setCourse] = React.useState('')
    const [emptyCourse, setEmptyCourse] = React.useState(false)
    const [english, setEnglish] = React.useState('')
    const [emptyEnglish, setEmptyEnglish] = React.useState(false)
    const [score, setScore] = React.useState('')
    const [passwordCheck, setPasswordCheck] = React.useState('')
    const [incorrectPW, setCorrectPW] = React.useState(false)

    const EMAILADDRESS = ['naver.com', 'gmail.com', 'dgu.ac.kr', 'daum.net', 'hanmail.com', 'nate.com']

    const onChangeEmail = (e) => { setEmail(e.target.value) }
    const onChangeEmailAddress = (e) => { setEmailAddress(e.target.value) }
    const onChangePassword = (e) => { setPassword(e.target.value) }
    const onChangeYear = (e) => { setYear(e.target.value) }
    const onChangeSemester = (e) => { setSemester(e.target.value) }
    const onChangeCourse = (e) => { setCourse(e.target.value) }
    const onChangeEnglish = (e) => { setEnglish(e.target.value) }
    const onChangeScore = (e) => { setScore(e.target.value) }
    const onChangePasswordCheck = (e) => { setPasswordCheck(e.target.value) }

    const data = {
        email: email + '@' + emailAddress,
        pw: password,
        year: year,
        semester: semester,
        course: course,
        english: english,
        score: score,
    }

    const onClickDuplication = (e) => {
        e.preventDefault()
        const body = {
            email: data.email
        }
        if (email === '') {
            setEmptyEmail(true)
            alert('ID를 입력하세요.')
        }
        else {
            setEmptyEmail(false)
            if (emailAddress === '') {
                setEmptyEmailAddress(true)
                alert('이메일주소를 고르세요.')
            }
            else {
                setEmptyEmailAddress(false)
                fetch("/emailcheck", {
                    method: 'post',
                    headers: {
                        "content-type": "application/json",
                    },
                    body: JSON.stringify(body),
                })
                    .then((res) => res.json())
                    .then((json) => {
                        console.log(json)

                        if (json.result === 1) {
                            // 중복
                            alert('중복된 ID입니다.')
                        }
                        else {
                            // 사용가능
                            setDuplicatedEmail(false)
                            alert('사용가능한 ID입니다.')
                        }

                    })
                    .catch()
            }
        }
    }

    const onClickSignUp = (e) => {
        e.preventDefault()
        if (email === '') { setEmptyEmail(true) }
        else { setEmptyEmail(false) }
        if (emailAddress === '') { setEmptyEmailAddress(true) }
        else { setEmptyEmailAddress(false) }
        if (password === '') { setEmptyPW(true) }
        else { setEmptyPW(false) }
        if (year === '') { setEmptyYear(true) }
        else { setEmptyYear(false) }
        if (semester === '') { setEmptySemester(true) }
        else { setEmptySemester(false) }
        if (course === '') { setEmptyCourse(true) }
        else { setEmptyCourse(false) }
        if (english === '') { setEmptyEnglish(true) }
        else { setEmptyEnglish(false) }
        if (password === passwordCheck) { setCorrectPW(false) }
        else { setCorrectPW(true) }


        if (duplicatedEmail) {
            alert('이메일 중복확인을 하세요.')
        }
        else {
            fetch("/signup", {
                method: 'post',
                headers: {
                    "content-type": "application/json",
                },
                body: JSON.stringify(data),
            })
                .then(res => console.log(res))
            alert("회원가입 성공!")
            window.location.replace('/')
        }
    }

    const YEAR = [2018, 2019, 2020, 2021, 2022];
    const SEMESTER = [8, 7, 6, 5, 4, 3, 2, 1];
    const COURSE = ["심화", "일반"];
    const ENGLISH = [0, 1, 2, 3, 4];
    const SCORE_TOEIC = [550, 600, 620, 650, 680, 700, 750, 800];

    return (
        <div className="fade-in">
            <Header />
            <Box className="signup">
                회원가입
            </Box>
            <Box className="text_area" component="form">
                <span style={{ fontSize: '14px' }}>가입정보</span>
                <Stack direction="row" alignItems="center" justifyContent="space-between" spacing={2}>
                    <Stack className="helperStack">
                        <Stack direction="row" alignItems="center" spacing={1}>
                            <TextField // 이메일 입력
                                disabled={!duplicatedEmail}
                                className="email"
                                error={emptyEmail}
                                name="id"
                                label="이메일"
                                variant="outlined"
                                size="small"
                                margin="normal"
                                onChange={onChangeEmail} />
                            <span style={{ marginTop: 6 }}>@</span>
                            <FormControl sx={{ width: 140 }} size="small">
                                <InputLabel id="emailAdress" sx={{ marginTop: 1 }}>이메일주소</InputLabel>
                                <Select
                                    disabled={!duplicatedEmail}
                                    className="select"
                                    error={emptyEmailAddress}
                                    labelId="emailAddress"
                                    value={emailAddress}
                                    name="emailAddress"
                                    label="이메일 주소"
                                    onChange={onChangeEmailAddress}
                                    sx={{ marginTop: 1 }}
                                >
                                    {
                                        EMAILADDRESS.map((emailAddress, idx) => {
                                            return <MenuItem key={idx} value={emailAddress}>{emailAddress}</MenuItem>
                                        })
                                    }
                                </Select>
                            </FormControl>
                        </Stack>
                        <span className="helper">{emptyEmail && '이메일을 입력하세요.'}</span>
                    </Stack>
                    <button disabled={!duplicatedEmail} onClick={onClickDuplication} className="check_btn" title="중복확인">
                        <Stack direction="row" alignItems="center" spacing={0.5}>
                            <CheckIcon fontSize="small" /><span className="check_text">중복확인</span>
                        </Stack>
                    </button>
                </Stack>
                <Stack direction="row" spacing={2}>
                    <Stack className="helperStack">
                        <TextField // 비밀번호 입력
                            className="text"
                            error={emptyPW}
                            value={password}
                            name="pw"
                            label="비밀번호"
                            type="Password"
                            size="small"
                            margin="normal"
                            onChange={onChangePassword} />
                        <span className="helper">{emptyPW && '비밀번호를 입력하세요.'}</span>
                    </Stack>
                    <Stack className="helperStack">
                        <TextField // 비밀번호 확인
                            className="text"
                            error={incorrectPW}
                            value={passwordCheck}
                            name="pw"
                            label="비밀번호 확인"
                            type="Password"
                            size="small"
                            margin="normal"
                            onChange={onChangePasswordCheck} />
                        <span className="helper">{incorrectPW && '비밀번호가 다릅니다.'}</span>
                    </Stack>
                </Stack>
            </Box>
            <Box className="select_area" component="form" mt={2}>
                <span style={{ fontSize: '14px' }}>개인정보</span>
                <Stack direction="row" spacing={2} mt={2}>
                    <FormControl fullWidth size="small">
                        <InputLabel id="year">입학년도</InputLabel>
                        <Select // 입학년도 선택
                            className="select"
                            error={emptyYear}
                            labelId="year"
                            value={year}
                            name="year"
                            label="입학년도"
                            onChange={onChangeYear}
                        >
                            {
                                YEAR.map((year, idx) => {
                                    return <MenuItem key={idx} value={year}>{year}학년도</MenuItem>
                                })
                            }
                        </Select>
                        <span className="helper" style={{ marginTop: '5px' }}>{emptyYear && '입학년도를 선택하세요.'}</span>
                    </FormControl>
                    <FormControl fullWidth size="small">
                        <InputLabel id="register">이수학기수</InputLabel>
                        <Select // 이수학기수 선택
                            className="select"
                            error={emptySemester}
                            labelId="semester"
                            value={semester}
                            name="semester"
                            label="이수학기수"
                            onChange={onChangeSemester}
                        >
                            {
                                SEMESTER.map((semester, idx) => {
                                    return <MenuItem key={idx} value={semester}>{semester}학기</MenuItem>
                                })
                            }
                        </Select>
                        <span className="helper" style={{ marginTop: '5px' }}>{emptySemester && '이수학기수을 선택하세요.'}</span>
                    </FormControl>
                </Stack>
                <Stack direction="row" spacing={2} mt={2}>
                    <FormControl fullWidth size="small">
                        <InputLabel id="course">심화/일반</InputLabel>
                        <Select // 심화/일반 과정 선택
                            className="select"
                            error={emptyCourse}
                            labelId="course"
                            value={course}
                            name="course"
                            label="심화/일반"
                            onChange={onChangeCourse}
                        >
                            {
                                COURSE.map((course, idx) => {
                                    return <MenuItem key={idx} value={course}>{course}</MenuItem>
                                })
                            }
                        </Select>
                        <span className="helper" style={{ marginTop: '5px' }}>{emptyCourse && '심화과정 여부를 선택하세요.'}</span>
                    </FormControl>
                    <FormControl fullWidth size="small">
                        <InputLabel id="english">영어레벨</InputLabel>
                        <Select // 영어 레벨 선택
                            className="select"
                            error={emptyEnglish}
                            labelId="english"
                            value={english}
                            name="english"
                            label="영어레벨"
                            onChange={onChangeEnglish}
                        >
                            {
                                ENGLISH.map((english, idx) => {
                                    return <MenuItem key={idx} value={english}>S{english}</MenuItem>
                                })
                            }
                        </Select>
                        <span className="helper" style={{ marginTop: '5px' }}>{emptyEnglish && '영어레벨을 선택하세요.'}</span>
                    </FormControl>
                </Stack>
                <Stack direction="row" justifyContent="space-between" mt={2}>
                    <FormControl fullWidth size="small" sx={{ maxWidth:'40%', minWidth: 80 }}>
                        <InputLabel id="score">외국어성적</InputLabel>
                        <Select
                            className="select"
                            labelId="score"
                            value={score}
                            name="score"
                            label="외국어성적"
                            onChange={onChangeScore}
                        >
                            {
                                SCORE_TOEIC.map((score, idx) => {
                                    return <MenuItem key={idx} value={score}>{score}</MenuItem>
                                })
                            }
                        </Select>
                    </FormControl>
                    <ConversionTable />
                </Stack>
            </Box>
            <Box className="btn_area">
                <button className="btn" variant="contained" onClick={onClickSignUp}>회원가입</button>
            </Box>
        </div>
    );
};

export default SignUp;