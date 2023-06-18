import React, { useState, useEffect } from "react";
import { Box, Select, Stack, MenuItem, InputLabel, FormControl } from '@mui/material';
import FileUploadOutlinedIcon from "@mui/icons-material/FileUploadOutlined";
import SideBar from '../Components/SideBar';
import * as XLSX from "xlsx";
import './css/CreateDataSet.css';
import {Link} from "react-router-dom";
import LogoutOutlinedIcon from "@mui/icons-material/LogoutOutlined";

const GradReqChange = () => {
    const COURSE = [
        "심화",
        "일반"
    ];
    const onClickLogout = () => {
        sessionStorage.clear()
        window.location.replace('/')
      }
    const input = React.useRef(null);
    const [placeholder, setPlaceholder] = useState("");
    const [course, setCourse] = useState("");
    const [filetype, setFiletype] = useState("");
    const [year, setYear] = useState("");
    const [reqinput, setReqinput] = React.useState([]);

    const handleCourseChange = (e) => {
        setCourse(e.target.value);
    }

    const handleYearChange = (e) => {
        setYear(e.target.value);
    };

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

    const handleNewDownload = () => {
        const link = document.createElement('a');
        link.download = '신설과목_추가';
        link.href = 'img/AddNewCourse.xlsx';
        link.click();
    }

    const currentYear = new Date().getFullYear();
    const yearOptions = Array.from({ length: currentYear - 2016 }, (_, index) => 2017 + index);
    const readExcel = async (file) => {
        const fileReader = await new FileReader();
        fileReader.readAsArrayBuffer(file);
        fileReader.onload = (e) => {
            const bufferArray = e?.target.result;
            const wb = XLSX.read(bufferArray, { type: "object" });
            const wsname = wb.SheetNames[0];
            const ws = wb.Sheets[wsname];

            const data = XLSX.utils.sheet_to_json(ws, { header: 'object' });

            const dataObject = {};
            data.forEach((item, index) => {
                dataObject[index.toString()] = item;
            });

            console.log(JSON.stringify(dataObject));
            console.log("dasdfafadfdsafasdf" + JSON.stringify(dataObject))
            setReqinput(JSON.stringify(dataObject));
        };

    };

    const onClickInput = async (e) => {
        if (!course) {
            alert("항목을 선택해주세요.");
            return;
        }
        const formData = new FormData();
        console.log("here!" + input.current.files);
        const file = input.current.files[0];
        let info
        if (course === "심화")
            info = filetype + "hard" + year;
        if (course === "일반")
            info = filetype + "general" + year;

        formData.append('file12', file);
        console.log("****" + file);
        console.log(info);


        try {
            const response = await fetch("/change/file", {
                method: 'POST',
                headers: {
                    "content-type": "application/json;charset=utf-8",
                    "X-File-Type": info,
                },
                body: reqinput,
            });

            if (response.ok) {
                alert("졸업요건변경을 완료하였습니다.");
                window.location.href = '/change'
            } else {
                alert("졸업요건변경을 실패하였습니다.")
                console.log("Error: ", response.body);
            }
        } catch (error) {
            alert("졸업요건변경을 실패하였습니다.")
            console.error("Error: ", error);
        }
    }

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
                <Box className="sub_title">졸업요건 업로드</Box>
                <Box className="guide">
                    {/* <img alt="컴공졸업요건입력예시" src="img/GradReqExample.png"></img> */}
                    {/* <img alt="학수번호변경예시" src="img/CourseNumChangeExample.png"></img> */}
                    {/* <img alt="신설과목예시" src="img/newcourse.png"></img> */}
                    <div style={{ fontSize: '20px', fontFamily: 'SsangMun', marginBottom: '15px' }}>[ 양식 다운로드 ]</div>
                    <button class='uploadbtn' onClick={handleReqDownload} variant="contained">
                        컴퓨터공학과 졸업요건 양식
                    </button>
                    <button class='uploadbtn' onClick={handleCourseDownload} variant="contained">
                        학수강좌번호 변경 양식
                    </button>
                    <button className='uploadbtn' onClick={handleNewDownload} variant="contained">
                        신설과목 추가 양식
                    </button>
                    <div class="subtitle">심화/일반 선택</div>
                    <FormControl sx={{ width: 100 }}>
                        <InputLabel htmlFor="course-select">심화/일반</InputLabel>
                        <Select
                            className="select"
                            value={course}
                            label="심화/일반"
                            labelId="course-select"
                            size="small"
                            onChange={handleCourseChange}
                            sx={{ marginTop: 1 }}
                        >
                            {COURSE.map((course, idx) => (
                                <MenuItem key={idx} value={course}>
                                    {course}
                                </MenuItem>
                            ))}
                        </Select>
                    </FormControl>
                    <div className="subtitle">년도 선택</div>
                    <FormControl sx={{ width: 100 }}>
                        <InputLabel htmlFor="year-select">년도</InputLabel>
                        <Select
                            className="select"
                            value={year}
                            label="년도"
                            labelId="year-select"
                            size="small"
                            onChange={handleYearChange}
                            sx={{ marginTop: 1 }}
                        >
                            {yearOptions.map((option) => (
                                <MenuItem key={option} value={option}>
                                    {option}
                                </MenuItem>
                            ))}
                        </Select>
                    </FormControl>
                </Box>
                <Stack className="input_area" flexDirection={"row"}>
                    <Stack
                        className="upload_btn"
                        flexDirection={"row"}
                        onClick={() => {
                            input.current?.click();
                        }}
                    >
                        <span className="upload_icon">
                            <FileUploadOutlinedIcon />
                        </span>
                        <span className="upload">파일 업로드</span>
                    </Stack>
                    <Box className="file_name" sx={{ overflow: "hidden" }}>{placeholder}</Box>
                    <input
                        type={"file"}
                        accept={".xlsx,.xls"}
                        ref={input}
                        style={{ display: "none" }}
                        onChange={(e) => {
                            const file = e.target.files[0];
                            readExcel(file);
                            setPlaceholder(file.name);
                        }}
                    />
                </Stack>

                <Box className="btn_area" style={{ marginBottom: 50 }}>
                    <button className="btn" onClick={onClickInput}>
                        Update
                    </button>
                </Box>
            </div>
        </div>
    );
};

export default GradReqChange;