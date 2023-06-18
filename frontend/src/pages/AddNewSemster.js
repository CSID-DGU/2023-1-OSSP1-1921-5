import React, { useState } from 'react';
import Stack from "@mui/material/Stack";
import Box from "@mui/material/Box";
import FileUploadOutlinedIcon from "@mui/icons-material/FileUploadOutlined";
import * as XLSX from "xlsx";
import SideBar from '../Components/SideBar';
import {Link} from "react-router-dom";
import LogoutOutlinedIcon from "@mui/icons-material/LogoutOutlined";

const AddNewSemester = () => {
    const input = React.useRef(null);
    const [placeholder, setPlaceholder] = useState("");
    const [year, setYear] = useState("");
    const [semester, setSemester] = useState("");
    const [lectureDatas, setLectureDatas] = useState([]);

    const handleYearChange = (event) => {
        setYear(event.target.value);
    };

    const handleSemesterChange = (event) => {
        setSemester(event.target.value);
    };

    const readExcel = async (file) => {
        const fileReader = await new FileReader();
        fileReader.readAsArrayBuffer(file);
    
        fileReader.onload = (e) => {
          const bufferArray = e?.target.result;
          const wb = XLSX.read(bufferArray, { type: "buffer" });
          const wsname = wb.SheetNames[0];
          const ws = wb.Sheets[wsname];
    
          const data = XLSX.utils.sheet_to_json(ws);
          console.log(data);

          /*
          (학수번호 ClassNumber), 
          (교수님성함 ProfessorName), 
          (교과목명 LectureNick), 
          (교과과정 Curriculum), 
          (교과영역구분 ClassArea), 
          (학점 ClassCredit), 
          (설계학점 DesignCredit) 
          (원어강의 )
          */
          
          try {
            for (var i = 0; i < data.length; i++) {
                const lectureData = {};
                lectureData["ClassNumber"] = data[i]["학수강좌번호"];
                lectureData["ProfessorName"] = data[i]["교원명"];
                lectureData["LectureNick"] = data[i]["교과목명"];
                lectureData["Curriculum"] = data[i]["교과과정"];
                lectureData["ClassArea"] = data[i]["이수구분"];
                lectureData["ClassCredit"] = data[i]["학점"];
                lectureData["DesignCredit"] = data[i]["공학설계"] ? data[i]["공학설계"].trim() : "0.0";

                if (data[i]["원어강의"] === "영어") {
                    lectureData["IsEnglish"] = 1
                } else {
                    lectureData["IsEnglish"] = 0
                }
                lectureDatas.push(lectureData);
            }

          } catch(error) {
            alert("올바른 형식의 엑셀 파일을 업로드해주세요.");
            setYear("");
            setSemester("");
            setLectureDatas([]);
            setPlaceholder("");
            input.current.value = null;
          }
          setLectureDatas(lectureDatas);
        };
    };
    
    const jsonResult = {
        year: year,
        semester: semester,
        lectureDataList: lectureDatas
    };
    const onClickLogout = () => {
        sessionStorage.clear()
        window.location.replace('/')
    }
    const onClickInput = (e) => {
        if (!year || !semester) {
            alert("년도와 학기를 입력해주세요.");
            return;
        }

        e.preventDefault();
            console.log(JSON.stringify(jsonResult));
            fetch("/uploadNewSemester", {
            method: "post",
            headers: {
                "content-type": "application/json",
            },
            body: JSON.stringify(jsonResult),
        })
        .then((response) => {
            if(response.ok) {
                alert("새로운 학기 강좌가 입력되었습니다.");
                window.location.href = "/newsem";
            } else {
                alert("강좌 입력 실패");
                window.location.href = "/newsem";
            }
        }) 
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
            <Box className="sub_title">강의 정보 등록</Box>
            <Box className="guide">
            <div style={{fontSize:'20px',fontFamily:'SsangMun',marginBottom:'15px'}}>[ 등록 정보 입력 ]</div>
            <Stack className="input_area" flexDirection={"row"}>
                <input 
                    className="textfield"
                    type="text"
                    value={year}
                    onChange={handleYearChange}
                    placeholder="년도"
                    variant="outlined"
                />
                <input 
                    className="textfield"
                    type="text"
                    value={semester}
                    onChange={handleSemesterChange}
                    placeholder="학기"
                    variant="outlined"
                />
            </Stack>
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

export default AddNewSemester;