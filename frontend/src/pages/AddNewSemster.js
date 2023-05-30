import React, { useState } from 'react';
import MenuBar from '../Components/MenuBar';
import Stack from "@mui/material/Stack";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import FileUploadOutlinedIcon from "@mui/icons-material/FileUploadOutlined";
import * as XLSX from "xlsx";
import './css/Input.css';


const AddNewSemester = () => {
    const input = React.useRef(null);
    const [placeholder, setPlaceholder] = useState("");
    const [year, setYear] = useState("");
    const [semester, setSemester] = useState("");
    const [lectureData, setLectureDatas] = useState([]);

    const handleYearChange = (event) => {
        setYear(event.target.value);
    };

    const handleSemesterChange = (event) => {
        setSemester(event.target.value);
    };

    const handleLectureDataChange = (event) => {
        setLectureDatas(event.target.value);
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
          setLectureDatas(data);
        };
    };
    
    const jsonResult = {
        year: year,
        semester: semester,
        lectureDataList: lectureData
    };

    const onClickInput = (e) => {
        e.preventDefault();
            console.log(JSON.stringify(jsonResult));
            fetch("", {
            method: "post",
            headers: {
                "content-type": "application/json",
            },
            body: JSON.stringify(jsonResult),
        })
        .then((response) => {
            if(response.ok) {
                alert("새로운 학기 강좌가 입력되었습니다.");
            } else {
                throw new Error("강좌 입력 실패");
            }
        }) 
    }
      
    return (
        <div>
            <MenuBar />
            <Stack className="input_area" flexDirection={"row"}>
                <TextField 
                    className="textfield"
                    type="text"
                    value={year}
                    onChange={handleYearChange}
                    label="년도"
                    variant="outlined"
                />
                <TextField 
                    className="textfield"
                    type="text"
                    value={semester}
                    onChange={handleSemesterChange}
                    label="학기"
                    variant="outlined"
                />
            </Stack>
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
    );
};

export default AddNewSemester;