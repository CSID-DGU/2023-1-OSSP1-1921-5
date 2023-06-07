import React, {useState} from 'react';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField, Button,} from '@mui/material';
import SideBar from '../Components/SideBar';
import Header from '../Components/Header';

/*
학수번호 변경 ex) CSE2016→CSE2028
교과명 변경 ex) 창의적공학설계→어드벤처디자인
필수과목 추가 ex) 23학번 세미나
필수과목 제거 ex) 기본소양.. 제거
이수학점 변경 ex) 23학번 공통교양 이수학점 17로 변경

현재 졸업요건을 나타내는 table이 기본적으로 있고 --> 필수과목 추가, 필수과목 제거, 이수학점 변경
학수번호 변경 같은 걸 하는... form을 또 따로 만들자. --> 학수번호 변경, 교과명 변경
*/

const GradReqChange = () => {
    const [formData, setFormData] = useState({
        liberalcredit: '', // 공통교양 이수학점
        liberal: '', // 공통교양 필수
        bsmcredit: '', // bsm(수학 + 과학) 이수학점
        bsm: '', // bsm 필수
        majorcredit: '', // 전공 이수학점
        major: '', // 전공 필수
        designcredit: '', // 설계 이수학점
        design: '', // 설계
        totalcredit: '', // 총이수학점
        totalscore: '', // 평점평균
        toeic: '', // 외국어시험
        english: '', // 영어강의 이수 개수 
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Handle form submission logic here
        console.log(formData);
        // Reset the form fields
        setFormData({
            name: '',
            email: '',
            message: '',
        });
    };

    return (
        <div className="fade-in">
        <Header signout />
        {/* <MenuBar /> <SideBar />*/}
        <div className="container">
            <h4>심화 과정</h4>
            <TextField></TextField>
            <h4>일반 과정</h4>
        </div>
        </div>
    );
};

export default GradReqChange;