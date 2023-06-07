import React, {useState} from 'react';
import { Button,} from '@mui/material';
import SideBar from '../Components/SideBar';
import Header from '../Components/Header';

/*
학수번호 변경 ex) CSE2016→CSE2028
교과명 변경 ex) 창의적공학설계→어드벤처디자인

학수번호 변경 같은 걸 하는 form을 따로 만들어야하나? --> 학수번호 변경, 교과명 변경
*/

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

    const handleDownload = () => {
        const link = document.createElement('a');
        link.download = 'Graduation-Requirement-File';
        link.href = 'img/GraduationRequirements.xlsx';
        link.click();
    }

    return (
        <div className="fade-in">
        <Header signout />
        {/* <SideBar />*/}
        <div className="container">
            <Button onClick={handleDownload} variant="contained">
                Download file
            </Button>
        </div>
        </div>
    );
};

export default GradReqChange;