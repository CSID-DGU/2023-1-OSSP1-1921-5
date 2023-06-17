import React, { useState, useEffect } from 'react';
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box'
import Stack from '@mui/material/Stack'
import MuiAccordion from '@mui/material/Accordion';
import MuiAccordionSummary from '@mui/material/AccordionSummary';
import MuiAccordionDetails from '@mui/material/AccordionDetails';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import './css/Result.css';
import Header from '../Components/Header';
import AlertModal from '../Components/AlertModal';
import LoadingSpinner from '../Components/LoadingSpinner';
import EssLectures from '../Components/EssLectures';

const Accordion = styled((props) => (
    <MuiAccordion disableGutters elevation={0} square {...props} />
))(({ theme }) => ({
    '&:before': {
        display: 'none',
    },
}));

const AccordionSummary = styled((props) => (
    <MuiAccordionSummary
        expandIcon={<ExpandMoreIcon />}
        {...props}
    />
))(({ theme }) => ({
    flexDirection: 'row',
    '& .MuiAccordionSummary-expandIconWrapper.Mui-expanded': {
        transform: 'rotate(180deg)',
    },
    '& .MuiAccordionSummary-content': {
        marginLeft: theme.spacing(-2),
        marginTop: theme.spacing(0.5),
        marginBottom: theme.spacing(1),
    },
}));

const AccordionDetails = styled(MuiAccordionDetails)(({ theme }) => ({
    padding: theme.spacing(1),
    marginTop: theme.spacing(2),
    backgroundColor: '#F6f6f6',
}));

const Result = () => {
    const [expanded, setExpanded] = useState('panel1');
    const [loading, setLoading] = useState(true);

    const [hasResult, setHasResult] = useState();
    const [course, setCourse] = useState();
    const [studentNumber, setStudentNumber] = useState();
    const [engLevel, setEngLevel] = useState();
    const [register, setRegister] = useState();
    const [engScore, setEngScore] = useState();
    const [totalCredit, setTotalCredit] = useState();
    const [commonClassCredit, setCommonClassCredit] = useState();
    const [gibonSoyangCredit, setGibonSoyangCredit] = useState();
    const [bsmCredit, setBsmCredit] = useState();
    const [bsmMathCredit, setBsmMathCredit] = useState();
    const [bsmSciCredit, setBsmSciCredit] = useState();
    const [majorCredit, setMajorCredit] = useState();
    const [specialMajorCredit, setSpecialMajorCredit] = useState();
    const [engClassCount, setEngClassCount] = useState();
    const [totalScore, setTotalScore] = useState();

    const [isGraduate, setIsGraduate] = useState();
    const [isRegister, setIsRegister] = useState();
    const [isEngScore, setIsEngScore] = useState();
    const [isTotalCredit, setIsTotalCredit] = useState();
    const [isCommonClassCredit, setIsCommonClassCredit] = useState();
    const [isBsmCredit, setIsBsmCredit] = useState();
    const [isMajorCredit, setIsMajorCredit] = useState();
    const [isEngClassCount, setIsEngClassCount] = useState();
    const [isTotalScore, setIsTotalScore] = useState();

    const [major_Credit, setmajor_Credit] = useState();
    const [total_Credit, settotal_Credit] = useState();

    const handleChange = (panel) => (event, newExpanded) => {
        setExpanded(newExpanded ? panel : false);
    };

    useEffect(() => {
        const fetchData = async () => {
            const data = {
                email: sessionStorage.getItem('userId')
            };

            try {
                const response = await fetch("/result", {
                    method: 'post',
                    headers: {
                        "content-type": "application/json",
                    },
                    body: JSON.stringify(data)
                });

                if (response.ok) {
                    const json = await response.json();
                    console.log(json)
                    setHasResult(json.Result)
                    setCourse(json.Course) // 심화/일반
                    setStudentNumber(json.StudentNumber) // 입학년도
                    setEngLevel(json.EngLevel) // 영어 레벨
                    setRegister(json.Register) // 이수 학기 수
                    setEngScore(json.EngScore) // 토익 점수
                    setTotalCredit(json.TotalCredit) // 총 이수 학점
                    setCommonClassCredit(json.CommonClassCredit) // 공통교양 이수 학점
                    setGibonSoyangCredit(json.GibonsoyangCredit) // 기본소양 이수 학점
                    setBsmCredit(json.BSMCredit) // bsm 이수 학점
                    setBsmMathCredit(json.BSMMathCredit) // bsm 중 수학 이수 학점
                    setBsmSciCredit(json.BSMSciCredit) // bsm 중 과학 이수 학점
                    setMajorCredit(json.MajorCredit) // 전공 이수 학점
                    setSpecialMajorCredit(json.SpecialMajorCredit) // 필수 전공 이수 학점
                    setEngClassCount(json.EngClassCount) // 영어 강의 이수 개수
                    setTotalScore(json.TotalScore) // 평점
                } else {
                    console.error("Error: ", response.status);
                }
            } catch(error) {
                console.error("Error: ", error);
            }
        };
        fetchData();

    }, []);

    return (
        <>
        
            <div className="fade-in">

                <Header mypage signout />
                {isGraduate ? 
                    <Stack className="result_stack" justifyContent="center" direction="row">
                        <span className="r0">졸업</span>
                        <span className="r2">가능</span>
                        <span className="r0">합니다! 🥳</span>
                    </Stack> :
                    <Stack className="result_stack" justifyContent="center" direction="row">
                        <span className="r0">졸업</span>
                        <span className="r1">불가능</span>
                        <span className="r0">합니다!</span>
                    </Stack>
                }
                <Box className="result_detail">
                    {isRegister ? 
                        <Box className="detail_box">
                            <div className="stack">
                                <img className="check_img" alt="check_img" src="img/yeah.png"></img>
                                <span className="detail_title">등록학기</span>
                            </div>
                            <span className="detail_content"><u>{register}학기</u>를 이수하였습니다.</span>
                        </Box> : 
                        <Box className="detail_box">
                            <div className="stack">
                                <img className="check_img" alt="check_img" src="img/nope.png"></img>
                                <span className="detail_title">등록학기</span>
                            </div>
                            <span className="detail_content"><u>{register}학기</u>를 이수하였습니다. <b style={{ color: 'crimson' }}>{8-register}학기</b>가 부족합니다.</span>
                        </Box>
                    }
                    {isEngScore ?
                        <Box className="detail_box">
                            <div className="stack">
                                <img className="check_img" alt="check_img" src="img/yeah.png"></img>
                                <span className="detail_title">외국어성적</span>
                            </div>
                            <span className="detail_content"><u>{engScore}점</u>으로 700점 이상입니다.</span>
                        </Box> :
                        <Box className="detail_box">
                        <div className="stack">
                            <img className="check_img" alt="check_img" src="img/nope.png"></img>
                            <span className="detail_title">외국어성적</span>
                        </div>
                        <span className="detail_content"><u>{engScore}점</u>으로 700점 <b style={{ color: 'crimson' }}>미만</b>입니다.</span>
                        </Box>
                    }
                    <Box className="detail_box" >
                        <Accordion className="acc" onChange={handleChange('panel1')}>
                            {isTotalCredit ?
                                <AccordionSummary aria-controls="panel1d-content">
                                    <div>
                                        <img className="check_img0" alt="check_img" src="img/yeah.png"></img>
                                        <span className="detail_title2">취득학점</span>
                                    </div>
                                    <span className="detail_content2">총 <u>{totalCredit}학점</u>으로 140학점 이상입니다.</span>
                                </AccordionSummary> :
                                <AccordionSummary aria-controls="panel1d-content">
                                <div>
                                    <img className="check_img0" alt="check_img" src="img/nope.png"></img>
                                    <span className="detail_title2">취득학점</span>
                                </div>
                                <span className="detail_content2">총 <u>{totalCredit}학점</u>으로 <b style={{ color: 'crimson' }}>{total_Credit}학점</b>이 부족합니다.</span>
                                </AccordionSummary>
                            } 
                            <AccordionDetails>
                                {isCommonClassCredit ?
                                    <Stack className="category" direction="row" spacing={1}>
                                        <img className="check_img2" alt="check_img" src="img/yeah.png"></img>
                                        <span className="category_title">공통교양</span>
                                        <span className="category_content">{commonClassCredit}학점 / 14학점</span>
                                    </Stack> :
                                    <Stack className="category" direction="row" spacing={1}>
                                        <img className="check_img2" alt="check_img" src="img/nope.png"></img>
                                        <span className="category_title">공통교양</span>
                                        <span className="category_content"><b style={{ color: 'crimson' }}>{commonClassCredit ? commonClassCredit : 0}학점</b> / 14학점</span>
                                    </Stack>
                                }
                                {isBsmCredit ?
                                    <Stack className="category" direction="row" spacing={1}>
                                        <img className="check_img2" alt="check_img" src="img/yeah.png"></img>
                                        <span className="category_title">학문기초</span>
                                        <span className="category_content">{bsmCredit}학점 / 21학점</span>
                                    </Stack> :
                                    <Stack className="category" direction="row" spacing={1}>
                                        <img className="check_img2" alt="check_img" src="img/nope.png"></img>
                                        <span className="category_title">학문기초</span>
                                        <span className="category_content"><b style={{ color: 'crimson' }}>{bsmCredit ? bsmCredit : 0}학점</b> / 21학점</span>
                                    </Stack>
                                }
                                {isMajorCredit ?
                                    <Stack className="category" direction="row" spacing={1}>
                                        <img className="check_img2" alt="check_img" src="img/yeah.png"></img>
                                        <span className="category_title">전공</span>
                                        <span className="category_content">{majorCredit}학점 / 84학점</span>
                                    </Stack> :
                                    <Stack className="category" direction="row" spacing={1}>
                                        <img className="check_img2" alt="check_img" src="img/nope.png"></img>
                                        <span className="category_title">전공</span>
                                        <span className="category_content"><b style={{ color: 'crimson' }}>{majorCredit ? majorCredit : 0}학점</b> / {major_Credit}학점</span>
                                    </Stack>
                                }
                            </AccordionDetails>
                        </Accordion>
                    </Box>
                    {isTotalScore ?
                        <Box className="detail_box">
                            <div className="stack">
                                <img className="check_img" alt="check_img" src="img/yeah.png"></img>
                                <span className="detail_title">성적평점</span>
                            </div>
                            <span className="detail_content"><u>{totalScore}점</u>으로 2.0점 이상입니다.</span>
                        </Box> :
                        <Box className="detail_box">
                            <div className="stack">
                                <img className="check_img" alt="check_img" src="img/nope.png"></img>
                                <span className="detail_title">성적평점</span>
                            </div>
                            <span className="detail_content"><u>{totalScore}점</u>으로 2.0점 <b style={{ color: 'crimson' }}>미만</b>입니다.</span>
                        </Box>
                    }
                    {isEngClassCount ?
                        <Box className="detail_box">
                            <div className="stack">
                                <img className="check_img" alt="check_img" src="img/yeah.png"></img>
                                <span className="detail_title">영어강의</span>
                            </div>
                            <span className="detail_content"><u>{engClassCount}강의</u>를 이수하였습니다.</span>
                        </Box> :
                        <Box className="detail_box">
                            <div className="stack">
                                <img className="check_img" alt="check_img" src="img/nope.png"></img>
                                <span className="detail_title">영어강의</span>
                            </div>
                            <span className="detail_content"><u>{engClassCount}강의</u>를 이수하였습니다. <b style={{ color: 'crimson' }}>{4-engClassCount}강의</b>가 부족합니다.</span>
                        </Box>
                    }
                    <EssLectures />
                </Box>
            </div>
        
        </>
    );
};

export default Result;