import React, { useState } from 'react';
import axios from 'axios';
import Modal from "@mui/material/Modal";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import "./FindPW.css";

const Admin = () => {
  const [dataNum, setDataNum] = useState('');
  const [admissionYear, setAdmissionYear] = useState('');
  const [completeSem, setCompleteSem] = useState('');
  const [subjects, setSubjects] = useState('');

  const [open, setOpen] = useState(false);

  const handleOpen = () => setOpen(true); 
  const handleClose = () => setOpen(false); 

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

    axios.post('http://localhost:3001/', parsedObject)
      .then((response) => {
        console.log('요청 전송 성공', response);
      })
      .catch((error) => {
        console.error('요청 중 오류', error);
      });
  };

  return (
    <div>
      <button onClick={handleOpen}>
        데이터셋 만들기
      </button>
      <Modal open={open} onClose={handleClose}>
        <Box className="modal">
          <div>
          <TextField
              type="text"
              value={dataNum}
              onChange={handleDataNumChange}
              placeholder="데이터 개수"
          />

          <TextField
              type="text"
              value={admissionYear}
              onChange={handleAdmissionYearChange}
              placeholder="입학 연도"
          />

          <TextField
              type="text"
              value={completeSem}
              onChange={handleCompleteSemChange}
              placeholder="이수 학기"
          />

          <textarea
              value={subjects}
              onChange={handleSubjectsChange}
              placeholder="이수 강의 정보"
          />

          <button onClick={handleParsingAndSubmit}>전송</button>
          </div>
        </Box>
      </Modal>
    </div>
  );
};

export default Admin;