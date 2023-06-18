import React, {useState} from 'react';
import { Box, Select, Stack, MenuItem, InputLabel, FormControl} from '@mui/material';
import FileUploadOutlinedIcon from "@mui/icons-material/FileUploadOutlined";
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
    const [filetype, setFiletype] = useState("");

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

    const readExcel = (file) => {
        if(file.name.includes('졸업요건')) {
            setFiletype('grad');
        } else if(file.name.includes('학수강좌번호')) {
            setFiletype('courseNum')
        } else if (file.name.includes('신설과목')) {
            setFiletype('new')
        } else {
            alert("파일의 이름이 변경되었는지 확인해주세요.");
            setPlaceholder("");
            input.current.value = null;
        }
    };
    
    const onClickInput = async (e) => {
        const formData = new FormData();
        const file = input.current.files[0];
        formData.append('file', file)
        
        try {
            const response = await fetch("/", {
                method: "post",
                body: formData,
                headers: {
                    "X-File-Type": filetype,
                },
            });

            if(response.ok) {
                alert("졸업요건변경을 완료하였습니다.");
                window.location.href = '/newsem'
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