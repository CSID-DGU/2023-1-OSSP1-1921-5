import React, {useState} from 'react';
import MenuBar from '../Components/MenuBar';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField, Button,} from '@mui/material';
import SideBar from '../Components/SideBar';

/*
학수번호 변경 ex) CSE2016→CSE2028
교과명 변경 ex) 창의적공학설계→어드벤처디자인
필수과목 추가 ex) 23학번 세미나
필수과목 제거 ex) 기본소양.. 제거
이수학점 변경 ex) 23학번 공통교양 이수학점 17로 변경

현재 졸업요건을 나타내는 table이 기본적으로 있고 --> 필수과목 추가, 필수과목 제거, 이수학점 변경
학수번호 변경 같은 걸 하는... form을 또 따로 만들자. --> 학수번호 변경, 교과명 변경
*/

const GradReqChange = () => {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        message: '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Handle form submission logic here
        console.log(formData);
        // Reset the form fields
        setFormData({
            name: '',
            email: '',
            message: '',
        });
    };

    return (
        <div style={{ display: 'flex' }}>
            {/* <SideBar /> <SideBar />*/}
            <h4>심화 과정</h4>
            <TableContainer className="tableContainer" style={{ flex: 1 }}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>구분</TableCell>
                            <TableCell>2023학년도</TableCell>
                            <TableCell>비고</TableCell>
                        </TableRow>
                        <TableRow></TableRow>
                    </TableHead>
                    <TableBody>
                        <TableRow>
                            <TableCell>교양</TableCell>
                            <TableCell></TableCell>
                            <TableCell>
                                <TextField
                                    name="name"
                                    value={formData.name}
                                    onChange={handleChange}
                                />
                            </TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>Email</TableCell>
                            <TableCell>
                                <TextField
                                    name="email"
                                    value={formData.email}
                                    onChange={handleChange}
                                />
                            </TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>Message</TableCell>
                            <TableCell>
                                <TextField
                                    name="message"
                                    value={formData.message}
                                    onChange={handleChange}
                                    multiline
                                    rows={4}
                                />
                            </TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell></TableCell>
                            <TableCell>
                                <Button variant="contained" onClick={handleSubmit}>
                                    Submit
                                </Button>
                            </TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
            </TableContainer>
            <h4>일반 과정</h4>
        </div>
    );
};

export default GradReqChange;