import React, { useState } from 'react';
import Box from "@mui/material/Box";
import './css/CreateDataSet.css';
import SideBar from '../Components/SideBar';
import Header from '../Components/Header';

const CreateDataSet = () => {
  const [dataNum, setDataNum] = useState('');
  const [admissionYear, setAdmissionYear] = useState('');
  const [completeSem, setCompleteSem] = useState('');
  const [subjects, setSubjects] = useState('');


  const handleDataNumChange = (event) => {
    setDataNum(event.target.value);
  };

  const handleAdmissionYearChange = (event) => {
    setAdmissionYear(event.target.value);
  };

  const handleCompleteSemChange = (event) => {
    setCompleteSem(event.target.value);
  };

  const handleSubjectsChange = (event) => {
    setSubjects(event.target.value);
  };

  const handleParsingAndSubmit = () => {
    if (!dataNum || !admissionYear || !completeSem || !subjects) {
      alert("항목을 모두 입력해주세요.");
      return;
    }

    const parsedObject = {
      dataNum: dataNum,
      admissionYear: admissionYear,
      completeSem: completeSem,
      subjects: {},
    };

    const subjectLines = subjects.split('\n');

    subjectLines.forEach((line, index) => {
      const key = `subject${index + 1}`;
      const subjects = line.split(',').map((subject) => subject.trim());
      parsedObject.subjects[key.trim()] = subjects;
    });

    console.log(parsedObject);

    fetch("/createDataSet", {
      method: "post",
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(parsedObject),
    })
      .then((response) => {
        if (response.ok) {
          alert("DataSet 생성 요청 성공");
          console.log('요청이 성공적으로 전송되었습니다.', response);
        } else {
          alert("요청 실패");
          console.error('요청이 실패하였습니다.', response);
          window.location.href = "/dataset";
        }
      })
      .catch((error) => {
        console.error('요청 전송 중 오류가 발생했습니다.', error);
      });
  };

  
  return (
    <div className="fade-in">
      <Header signout />
      <SideBar />
      <div className="mainBox">
      <Box className="sub_title">TEST Dataset 생성</Box>
        <Box className="guide">
        <div style={{fontSize:'20px',fontFamily:'SsangMun',marginBottom:'15px'}}>[ 생성 정보 입력 ]</div>
          <div>
            <input
                className="textfield"
                type="text"
                value={dataNum}
                onChange={handleDataNumChange}
                placeholder="생성 Data 개수"
                variant="standard"
            />

            <input
                className="textfield"
                type="text"
                value={admissionYear}
                onChange={handleAdmissionYearChange}
                placeholder="입학년도"
                variant="standard"
            />

            <input
                className="textfield"
                type="text"
                value={completeSem}
                onChange={handleCompleteSemChange}
                placeholder="이수 학기"
                variant="standard"
            />

            <input
                className="textfield"
                value={subjects}
                onChange={handleSubjectsChange}
                placeholder="이수 강의 정보"
                variant="standard"
                multiline
            />
          <button className="btn" onClick={handleParsingAndSubmit}>생성</button>
          </div>
        </Box>
      </div>
      </div>
  );
};

export default CreateDataSet;