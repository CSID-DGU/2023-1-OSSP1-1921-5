import React, {useState} from 'react';
import { Button,} from '@mui/material';
import SideBar from '../Components/SideBar';
import Header from '../Components/Header';

const GradReqChange = () => {
    const [formData, setFormData] = useState({
        liberalCredit: '', // 공통교양 이수학점
        liberalReq: '', // 공통교양 필수
        bsmCredit: '', // bsm(수학 + 과학) 이수학점
        bsmReq: '', // bsm 필수
        majorCredit: '', // 전공 이수학점
        majorReq: '', // 전공 필수
        designCredit: '', // 설계 이수학점
        designReq: '', // 설계
        totalCredit: '', // 총이수학점
        totalScore: '', // 평점평균
        toeic: '', // 외국어시험s
        english: '', // 영어강의 이수 개수 
    });

    const handleReqDownload = () => {
        const link = document.createElement('a');
        link.download = '컴퓨터공학과_졸업요건_양식';
        link.href = 'img/GraduationRequirements.xlsx';
        link.click();
    }

    const handleCourseDownload = () => {
        const link = document.createElement('a');
        link.download = '학수강좌번호_변경';
        link.href = 'img/CourseNumChange.xlsx';
        link.click();
    }

    return (
        <div className="fade-in">
        <Header signout />
        {/* <SideBar />*/}
        <div className="container">
        {/* <img alt="컴공졸업요건입력예시" src="img/GradReqExample.png"></img> */}
        {/* <img alt="학수번호변경예시" src="img/CourseNumChangeExample.png"></img> */}
            <Button onClick={handleReqDownload} variant="contained">
                컴퓨터공학과 졸업요건 양식
            </Button>
            <Button onClick={handleCourseDownload} variant="contained">
                학수강좌번호 변경
            </Button>
        </div>
        </div>
    );
};

export default GradReqChange;