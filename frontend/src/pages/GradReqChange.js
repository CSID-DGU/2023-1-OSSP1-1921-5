import React, {useState} from 'react';
import { Box, Select, Stack, MenuItem, InputLabel, FormControl} from '@mui/material';
import FileUploadOutlinedIcon from "@mui/icons-material/FileUploadOutlined";
import * as XLSX from "xlsx";
import SideBar from '../Components/SideBar';
import Header from '../Components/Header';
import './css/CreateDataSet.css';

const GradReqChange = () => {
    const COURSE = [
        "심화",
        "일반"
    ];

    const input = React.useRef(null);
    const [placeholder, setPlaceholder] = useState("");
    const [course, setCourse] = useState("");
    const [formData, setFormData] = useState({
        liberalCredit: '', // 공통교양 이수학점
        liberalReq: [], // 공통교양 필수
        bsmCredit: '', // bsm(수학 + 과학) 이수학점
        bsmReq: [], // bsm 필수
        majorCredit: '', // 전공 이수학점
        majorReq: [], // 전공 필수
        designCredit: '', // 설계 이수학점
        totalCredit: '', // 총이수학점
        totalScore: '', // 평점평균
        toeic: '', // 외국어시험
        english: '', // 영어강의 이수 개수 
    });

    const onChangeCourse = (e) => {
        setCourse(e.target.value);
    }

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
          
          try {
            for (var i = 0; i < data.length; i++) {
                
            }

          } catch(error) {
            alert("올바른 형식의 엑셀 파일을 업로드해주세요.");
            setPlaceholder("");
            input.current.value = null;
          }
        };
    };

    const onClickInput = (e) => {
 
    }

    return (
        <div className="fade-in">
            <Header signout />
            <SideBar />
            <div className="mainBox">
            <Box className="sub_title">졸업요건 업로드</Box>
            <Box className="guide">
                {/* <img alt="컴공졸업요건입력예시" src="img/GradReqExample.png"></img> */}
                {/* <img alt="학수번호변경예시" src="img/CourseNumChangeExample.png"></img> */}
                {/* <img alt="신설과목예시" src="img/newcourse.png"></img> */}
                <div style={{fontSize:'20px',fontFamily:'SsangMun',marginBottom:'15px'}}>[ 양식 다운로드 ]</div>
                <button class='uploadbtn' onClick={handleReqDownload} variant="contained">
                    컴퓨터공학과 졸업요건 양식
                </button>
                <button class ='uploadbtn' onClick={handleCourseDownload} variant="contained">
                    학수강좌번호 변경 양식
                </button>
                <button className='uploadbtn' onClick={handleNewDownload} variant="contained">
                    신설과목 추가 양식
                </button>
                <div class="subtitle">심화/일반 선택</div>
                <FormControl sx={{ width: 100 }}>
                <InputLabel>
                    심화/일반
                </InputLabel>
                <Select
                    className="select"
                    value={course}
                    label="심화/일반"
                    size="small"
                    onChange={onChangeCourse}
                    sx={{ marginTop: 1 }}
                >
                    {COURSE.map((course, idx) => {
                    return (
                      <MenuItem key={idx} value={course}>
                        {course}
                      </MenuItem>
                    );
                  })}
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