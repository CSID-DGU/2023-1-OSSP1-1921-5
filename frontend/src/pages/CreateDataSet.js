import React, { useState } from 'react';
import Box from "@mui/material/Box";
import { TextField } from "@material-ui/core";
import './css/CreateDataSet.css';
import SideBar from '../Components/SideBar';
import FileDownloader from '../Components/FileDownloader'
import {Link} from "react-router-dom";
import Stack from "@mui/material/Stack";
import LogoutOutlinedIcon from "@mui/icons-material/LogoutOutlined";

const CreateDataSet = () => {
  const [dataNum, setDataNum] = useState('');
  const [admissionYear, setAdmissionYear] = useState('');
  const [completeSem, setCompleteSem] = useState('');
  const [subjects, setSubjects] = useState('');
  const [showFileDownloader, setShowFileDownloader] = useState(false);

  const onClickLogout = () => {
    sessionStorage.clear()
    window.location.replace('/')
  }
  
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
      parsedObject.subjects[key] = subjects;
    });

    console.log(parsedObject);

    fetch("/dataset/create", {
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
          setShowFileDownloader(true);
        } else {
          console.log(response.body)
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
            <Box className="tool" title="로그아웃">
                <Link to="/">
                    <Stack direction="row" onClick={onClickLogout}>
                        <LogoutOutlinedIcon /><div className="tool_title">로그아웃</div>
                    </Stack>
                </Link>
            </Box>
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

            <TextField
                id="textfield"
                type="text"
                value={subjects}
                onChange={handleSubjectsChange}
                placeholder="이수 강의 정보"
                variant="standard"
            />
            <br></br><br></br><br></br>
          <button className="btn" onClick={handleParsingAndSubmit}>생성</button>
            {showFileDownloader && <FileDownloader />}
          </div>
        </Box>
      </div>
      </div>
  );
};

export default CreateDataSet;