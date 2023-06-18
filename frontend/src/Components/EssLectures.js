import React, { useState, useEffect } from "react";
import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Stack from "@mui/material/Stack";
import MuiAccordion from "@mui/material/Accordion";
import MuiAccordionSummary from "@mui/material/AccordionSummary";
import MuiAccordionDetails from "@mui/material/AccordionDetails";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import "../pages/css/Result.css";
import LoadingSpinner from "../Components/LoadingSpinner";

const Accordion = styled((props) => (
  <MuiAccordion disableGutters elevation={0} square {...props} />
))(({ theme }) => ({
  "&:before": {
    display: "none",
  },
}));

const AccordionSummary = styled((props) => (
  <MuiAccordionSummary expandIcon={<ExpandMoreIcon />} {...props} />
))(({ theme }) => ({
  flexDirection: "row",
  "& .MuiAccordionSummary-expandIconWrapper.Mui-expanded": {
    transform: "rotate(180deg)",
  },
  "& .MuiAccordionSummary-content": {
    marginLeft: theme.spacing(-2),
    marginTop: theme.spacing(0.5),
    marginBottom: theme.spacing(1),
  },
}));

const AccordionDetails = styled(MuiAccordionDetails)(({ theme }) => ({
  padding: theme.spacing(1),
  marginTop: theme.spacing(2),
  backgroundColor: "#F6f6f6",
}));

const EssLectures = () => {
  const [expanded, setExpanded] = useState("panel1");
  const [loading, setLoading] = useState(true);

  const [course, setCourse] = useState();
  const [studentNumber, setStudentNumber] = useState();
  const [engLevel, setEngLevel] = useState();
  const [notTakingNC, setNotTakingNC] = useState(["NULL"]);
  const [notTakingBSM_GS, setNotTakingBSM_GS] = useState(["NULL"]);
  const [notTakingMJ, setNotTakingMJ] = useState(["NULL"]);
  const [isTakingNecessaryClass, setIsTakingNecessaryClass] = useState();

  const handleChange = (panel) => (event, newExpanded) => {
    setExpanded(newExpanded ? panel : false);
  };

    useEffect(() => {
        const fetchData = async () => {
            const data = {
                email: sessionStorage.getItem("userId"),
            };

            try {
                const response = await fetch("/result/essLectures", {
                    method: "post",
                    headers: {
                        "content-type": "application/json",
                    },
                    body: JSON.stringify(data),
                });

                if (!response.ok) {
                    throw new Error("Network response was not ok");
                }

                const json = await response.json();

                setCourse(json.Course);
                setStudentNumber(json.StudentNumber);
                setEngLevel(json.EngLevel);
                setNotTakingNC(json.notTakingNC);
                setNotTakingBSM_GS(json.notTakingBSM);
                setNotTakingMJ(json.notTakingMJ);

                if (notTakingNC.length) {
                    setIsTakingNecessaryClass(false);
                } else if (notTakingBSM_GS.length) {
                    setIsTakingNecessaryClass(false);
                } else if (notTakingMJ.length) {
                    if (
                        notTakingMJ.length === 1 &&
                        notTakingMJ[0] === "계산적사고법" &&
                        course === "일반" &&
                        studentNumber >= 2017
                    ) {
                        notTakingMJ.pop();
                    } else {
                        setIsTakingNecessaryClass(false);
                    }
                } else {
                    setIsTakingNecessaryClass(true);
                }

                console.log({
                    notTakingNC: notTakingNC,
                    notTakingBSM_GS: notTakingBSM_GS,
                    notTakingMJ: notTakingMJ,
                });
                console.log(isTakingNecessaryClass);

                setTimeout(() => {
                    setLoading(false);
                    console.log("로딩종료");
                }, 5000);
            } catch (error) {
                console.error("Error:", error);
            }
        };

        fetchData();
    }, []);

  return (
    <>
      {loading ? (
        <LoadingSpinner op={false} />
      ) : (
        <Box className="final_detail_box">
          <Accordion className="acc" onChange={handleChange("panel1")}>
            {isTakingNecessaryClass ? (
              <>
                {notTakingNC[0] == "NULL" ||
                notTakingBSM_GS[0] == "NULL" ||
                notTakingMJ[0] == "NULL" ? (
                  <div
                    style={{
                      textAlign: "right",
                      fontSize: 12,
                      color: "#007FFF",
                    }}
                  >
                    ⚠️ 값이 바로 뜨지 않을 수 있습니다. 잠시만 기다려주세요!
                  </div>
                ) : (
                  <></>
                )}
                <AccordionSummary aria-controls="panel1d-content">
                  <div>
                    <img
                      className="check_img0"
                      alt="check_img"
                      src="img/yeah.png"
                    ></img>
                    <span className="detail_title2">필수강의</span>
                  </div>
                  <span className="detail_content2">
                    필수강의를 모두 이수하였습니다.
                  </span>
                </AccordionSummary>
              </>
            ) : (
              <>
                {notTakingNC[0] == "NULL" ||
                notTakingBSM_GS[0] == "NULL" ||
                notTakingMJ[0] == "NULL" ? (
                  <div
                    style={{
                      textAlign: "right",
                      fontSize: 12,
                      color: "#007FFF",
                    }}
                  >
                    ⚠️ 값이 바로 뜨지 않을 수 있습니다. 잠시만 기다려주세요!
                  </div>
                ) : (
                  <></>
                )}
                <AccordionSummary aria-controls="panel1d-content">
                  <div>
                    <img
                      className="check_img0"
                      alt="check_img"
                      src="img/nope.png"
                    ></img>
                    <span className="detail_title2">필수강의</span>
                  </div>
                  <span className="detail_content2">
                    <b style={{ color: "crimson" }}>미이수</b>한 필수강의가
                    있습니다.
                  </span>
                </AccordionSummary>
              </>
            )}
            <AccordionDetails>
              <Stack direction="row" spacing={2} alignItems="center">
                {!notTakingNC.length ? (
                  <Stack className="category" direction="row" spacing={1}>
                    <img
                      className="check_img2"
                      alt="check_img"
                      src="img/yeah.png"
                    ></img>
                    <span className="category_title">공통교양</span>
                    <span className="category_content">모두 이수</span>
                  </Stack>
                ) : (
                  <Stack direction={{ xs: "column", sm: "row" }}>
                    <Stack className="category" direction="row" spacing={1}>
                      <img
                        className="check_img2"
                        alt="check_img"
                        src="img/nope.png"
                      ></img>
                      <span className="category_title">공통교양</span>
                      <span className="category_content" style={{ width: 80 }}>
                        <b style={{ color: "crimson" }}>
                          {notTakingNC.length}개
                        </b>{" "}
                        미이수
                      </span>
                    </Stack>
                    <div className="badge_box">
                      {notTakingNC.map((names, idx) => (
                        <div style={{ display: "inline" }} key={idx}>
                          <span className="badge">{names}</span>
                        </div>
                      ))}
                    </div>
                  </Stack>
                )}
              </Stack>
              <Stack direction="row" spacing={2} alignItems="center">
                {!notTakingBSM_GS.length ? (
                  <Stack className="category" direction="row" spacing={1}>
                    <img
                      className="check_img2"
                      alt="check_img"
                      src="img/yeah.png"
                    ></img>
                    <span className="category_title">학문기초</span>
                    <span className="category_content">모두 이수</span>
                  </Stack>
                ) : (
                  <Stack direction={{ xs: "column", sm: "row" }}>
                    <Stack className="category" direction="row" spacing={1}>
                      <img
                        className="check_img2"
                        alt="check_img"
                        src="img/nope.png"
                      ></img>
                      <span className="category_title">학문기초</span>
                      <span className="category_content" style={{ width: 80 }}>
                        <b style={{ color: "crimson" }}>
                          {notTakingBSM_GS.length}개
                        </b>{" "}
                        미이수
                      </span>
                    </Stack>
                    <div className="badge_box">
                      {notTakingBSM_GS.map((names, idx) => (
                        <div style={{ display: "inline" }} key={idx}>
                          <span className="badge">{names}</span>
                        </div>
                      ))}
                    </div>
                  </Stack>
                )}
              </Stack>
              <Stack direction="row" spacing={2} alignItems="center">
                {!notTakingMJ.length ? (
                  <Stack className="category" direction="row" spacing={1}>
                    <img
                      className="check_img2"
                      alt="check_img"
                      src="img/yeah.png"
                    ></img>
                    <span className="category_title">전공</span>
                    <span className="category_content">모두 이수</span>
                  </Stack>
                ) : (
                  <Stack direction={{ xs: "column", sm: "row" }}>
                    <Stack className="category" direction="row" spacing={1}>
                      <img
                        className="check_img2"
                        alt="check_img"
                        src="img/nope.png"
                      ></img>
                      <span className="category_title">전공</span>
                      <span className="category_content" style={{ width: 80 }}>
                        <b style={{ color: "crimson" }}>
                          {notTakingMJ.length}개
                        </b>{" "}
                        미이수
                      </span>
                    </Stack>
                    <div className="badge_box">
                      {notTakingMJ.map((names, idx) => (
                        <div style={{ display: "inline" }} key={idx}>
                          <span className="badge">{names}</span>
                        </div>
                      ))}
                    </div>
                  </Stack>
                )}
              </Stack>
            </AccordionDetails>
          </Accordion>
        </Box>
      )}
    </>
  );
};

export default EssLectures;
