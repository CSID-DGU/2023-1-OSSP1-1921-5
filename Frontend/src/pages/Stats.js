import React, { useEffect, useState } from 'react';
import Box from '@mui/material/Box'
import Stack from '@mui/material/Stack'
import LChart from '../Components/LChart';
import Header from '../Components/Header';
import './css/Stats.css';
import LoadingSpinner from '../Components/LoadingSpinner';

const Stats = () => {
    const [loading, setLoading] = useState(true);
    const [AllScoreData, setAllScoreData]  = useState();
    const [MajorScoreData, setMajorScoreData]  = useState();
    const [CreditData, setCreditData]  = useState();

    useEffect(() => {
        console.log('useEffect')
        const data = {
            email: sessionStorage.getItem('userId')
        }

        fetch("/semester", {
            method: 'post',
            headers: {
                "content-type": "application/json",
            },
            body: JSON.stringify(data)
        })
            .then((res) => res.json())
            .then((json) => {
                console.log(json)
                for (var i = 0; i < json.Semester; i++) {
                    const data = {
                        email: json.email,
                        TNumber: json.TNumList[i],
                        semester: i
                    }
                    fetch("/stats", {
                        method: 'post',
                        headers: {
                            "content-type": "application/json",
                        },
                        body: JSON.stringify(data)
                    })
                        .then((res) => res.json())
                        .then((json) => {
                            console.log(json)
                            fetch("/updatestat", {
                                method: 'post',
                                headers: {
                                    "content-type": "application/json",
                                },
                                body: JSON.stringify(json)
                            })
                                .then((res) => res.json())
                                .then((json) => {
                                    console.log('updatestat')
                                })

                        })
                }
            })
        fetch("/getstats", {
            method: 'post',
            headers: {
                "content-type": "application/json",
            },
            body: JSON.stringify(data)
        })
            .then((res) => res.json())
            .then((json) => {
                console.log(json)
                setAllScoreData(json[0])
                setMajorScoreData(json[1])
                setCreditData(json[2])
                console.log("AllScoreData", AllScoreData, "MajorScoreData", MajorScoreData, "CreditData", CreditData)
                setLoading(false)
                console.log("로딩종료")
            })
    })

    return (
        <>
        {loading ? <LoadingSpinner op={true} /> : (
            <div className="fade-in">
                <Header mypage signout />
                <Box className="sub_title">
                통계
                </Box>
                <Stack 
                    style={{margin:'20px 80px 80px 80px'}} 
                    direction={{xs:'column', sm:'row'}}
                    justifyContent="center"
                    alignItems="center"
                >
                    <LChart data={AllScoreData} title="전체평점 비교" dataKey="평점" />
                    <LChart data={MajorScoreData} title="전공평점 비교" dataKey="평점" />
                    <LChart data={CreditData} title="이수학점 비교" dataKey="학점" />
                </Stack>
            </div>
        )}
        </>
    )
}


export default Stats;