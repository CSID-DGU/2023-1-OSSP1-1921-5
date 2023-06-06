import React, { useState } from 'react';
import MenuBar from '../Components/MenuBar';
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
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
      {/* <MenuBar /> */}
      <SideBar />
      <div className="container">
        <div className="titleData">Test DataSet 생성</div>
        <Box className="Box">
          <div>
            <TextField
                className="textfield"
                type="text"
                value={dataNum}
                onChange={handleDataNumChange}
                label="생성 Data 개수"
                variant="outlined"
            />

            <TextField
                className="textfield"
                type="text"
                value={admissionYear}
                onChange={handleAdmissionYearChange}
                label="입학년도"
                variant="outlined"
            />

            <TextField
                className="textfield"
                type="text"
                value={completeSem}
                onChange={handleCompleteSemChange}
                label="이수 학기"
                variant="outlined"
            />

            <TextField
                className="textfield"
                value={subjects}
                onChange={handleSubjectsChange}
                label="이수 강의 정보"
                variant="outlined"
                multiline
            />

            <Button onClick={handleParsingAndSubmit}>전송</Button>
          </div>
        </Box>
      </div>
    </div>
  );
};

export default CreateDataSet;