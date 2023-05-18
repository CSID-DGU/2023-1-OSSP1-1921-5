import React, { Component } from 'react'
import Select from '@mui/material/Select'
import MenuItem from '@mui/material/MenuItem'
import FormControl from '@mui/material/FormControl'
import InputLabel from '@mui/material/InputLabel'
import TextField from '@mui/material/TextField'
import Box from '@mui/material/Box'
import Stack from '@mui/material/Stack'
import './css/UserInfo.css';
import Header from '../Components/Header';

class MyPage extends Component {
    state = {
        id: sessionStorage.getItem('userId'),
        pw: '',
        year: '',
        register: '',
        course: '',
        english: '',
        score: '',
    }

    componentDidMount() {
        this.setState({
            id: sessionStorage.getItem('userId')
        })
        const data = {
            email: this.state.id
        }
        fetch("/mypage", {
            method: 'post',
            headers: {
                "content-type": "application/json",
            },
            body: JSON.stringify(data)
        })
            .then((res) => res.json())
            .then((json) => {
                this.setState({
                    year: json.StudentNumber,
                    register: json.Semester,
                    course: json.Course,
                    english: json.EnglishGrade,
                    score: json.Score
                })
            })

    }

    appChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    appClick = () => {
        const data = {
            email: this.state.id,
            year: this.state.year,
            register: this.state.register,
            course: this.state.course,
            english: this.state.english,
            score: this.state.score
        }

        fetch("/updateuserinfo", {
            method: 'post',
            headers: {
                "content-type": "application/json",
            },
            body: JSON.stringify(data)
        })
        alert('변경이 완료되었습니다.')
        document.location.href = '/'
    }

    render() {
        const { id, year, register, course, english, score } = this.state;
        const { appChange, appClick } = this;
        const YEAR = [2018, 2019, 2020, 2021, 2022];
        const REGISTER = [8, 7, 6, 5, 4, 3, 2, 1];
        const COURSE = ["심화", "일반"];
        const ENGLISH = [0, 1, 2, 3, 4];
        const SCORE_TOEIC = [550, 600, 620, 650, 680, 700, 750, 800];

        return (
            <div className="fade-in">
                <Header signout />
                <Box className="mypage">
                    마이페이지
                </Box>
                <Box className="text_area" component="form">
                    <span style={{ fontSize: '14px' }}>가입정보</span>
                    <Stack direction="row" spacing={2}>
                        <TextField
                            className="text"
                            disabled
                            defaultValue={id}
                            name="id"
                            label="이메일"
                            variant="outlined"
                            size="small"
                            margin="normal"
                            onChange={appChange}
                            sx={{ width: 250 }} />
                    </Stack>
                </Box>
                <br />
                <Box className="select_area">
                    <span style={{ fontSize: '14px' }}>개인정보</span>
                    <Stack direction="row" spacing={2} mt={2}>
                        <FormControl fullWidth size="small">
                            <InputLabel id="year">입학년도</InputLabel>
                            <Select
                                className="select"
                                defaultValue={2019}
                                labelId="year"
                                value={year}
                                name="year"
                                label="입학년도"
                                onChange={appChange}
                            >
                                {
                                    YEAR.map((year, idx) => {
                                        return <MenuItem key={idx} value={year}>{year}학년도</MenuItem>
                                    })
                                }
                            </Select>
                        </FormControl>
                        <FormControl fullWidth size="small">
                            <InputLabel id="register">이수학기수</InputLabel>
                            <Select
                                className="select"
                                labelId="register"
                                value={register}
                                name="register"
                                label="이수학기수"
                                onChange={appChange}
                            >
                                {
                                    REGISTER.map((register, idx) => {
                                        return <MenuItem key={idx} value={register}>{register}학기</MenuItem>
                                    })
                                }
                            </Select>
                        </FormControl>
                    </Stack>
                    <Stack direction="row" spacing={2} mt={2}>
                        <FormControl fullWidth size="small">
                            <InputLabel id="course">심화/일반</InputLabel>
                            <Select
                                className="select"
                                labelId="course"
                                value={course}
                                name="course"
                                label="심화/일반"
                                onChange={appChange}
                            >
                                {
                                    COURSE.map((course, idx) => {
                                        return <MenuItem key={idx} value={course}>{course}</MenuItem>
                                    })
                                }
                            </Select>
                        </FormControl>
                        <FormControl fullWidth size="small">
                            <InputLabel id="english">영어레벨</InputLabel>
                            <Select
                                className="select"
                                labelId="english"
                                value={english}
                                name="english"
                                label="영어레벨"
                                onChange={appChange}
                            >
                                {
                                    ENGLISH.map((english, idx) => {
                                        return <MenuItem key={idx} value={english}>S{english}</MenuItem>
                                    })
                                }
                            </Select>
                        </FormControl>
                    </Stack>
                    <Stack direction="row" spacing={2} mt={2}>
                    <FormControl fullWidth size="small" sx={{ maxWidth:'40%', minWidth: 80 }}>
                        <InputLabel id="score">외국어성적</InputLabel>
                        <Select
                            className="select"
                            labelId="score"
                            value={score}
                            name="score"
                            label="외국어성적"
                            onChange={appChange}
                        >
                            {
                                SCORE_TOEIC.map((score, idx) => {
                                    return <MenuItem key={idx} value={score}>{score}</MenuItem>
                                })
                            }
                        </Select>
                    </FormControl>
                    </Stack>
                </Box>
                <Box className="btn_area">
                    <button className="btn" variant="contained" onClick={appClick}>수정</button>
                </Box>
            </div>
        );
    }
};

export default MyPage;