import React, { useState } from 'react';
import axios from 'axios';
import MenuBar from '../Components/MenuBar';
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import './css/CreateDataSet.css';

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
    const parsedObject = {
      dataNum: dataNum,
      admissionYear: admissionYear,
      completeSem: completeSem,
      subjects: {},
    };

    const subjectLines = subjects.split('\n');

    subjectLines.forEach((line) => {
      const [key, value] = line.split(':');
      const subjects = value.split(',').map((subject) => subject.trim());
      parsedObject.subjects[key.trim()] = subjects;
    });

    console.log(parsedObject);

    axios.post('/dataset', parsedObject)
      .then((response) => {
        console.log('요청 전송 성공', response);
      })
      .catch((error) => {
        console.error('요청 중 오류', error);
      });
  };


  return (
    <div>
      <MenuBar />
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