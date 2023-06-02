import { React, useState } from "react";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import Stack from "@mui/material/Stack";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import SendIcon from "@mui/icons-material/Send";
import "./FindPW.css";

const FindPW = () => {
  const EMAILADDRESS = [
    "naver.com",
    "gmail.com",
    "dgu.ac.kr",
    "daum.net",
    "hanmail.com",
    "nate.com",
  ];

  const [open, setOpen] = useState(false); // 비밀번호 찾기 실행했니? 응->open이 true. -> modal 열림. false면 닫혀.
  const [email, setEmail] = useState(""); // 사용자의 email
  const [emailAddress, setEmailAddress] = useState(""); // 사용자의 email 주소
  const [inputSecurityCode, setInputSecurityCode] = useState(""); // 사용자가 입력한 보안코드 가져옴
  const [securityCode, setSecurityCode] = useState(""); // 실제 보안코드
  const [password, setPassword] = useState(""); // 변경할 비밀번호
  const [passwordCheck, setPasswordCheck] = useState(""); // 비밀번호 확인
  const [emailCheck, setEmailCheck] = useState(false); // 입력한 email이 DB에 존재하는가?
  const [securityCheck, setSecurityCheck] = useState(false); // 실제와 입력 받은 보안코드가 일치할 때 true
  const [emptyEmail, setEmptyEmail] = useState(false); // email 입력 안했으면 true
  const [emptyEmailAddress, setEmptyEmailAddress] = useState(false); // email 주소 입력 안했으면 true
  const [emptyPW, setEmptyPW] = useState(false); // 변경할 pw 입력 안했으면 true
  const [incorrectPW, setCorrectPW] = useState(false); // 변경할 pw와 확인 pw가 다르면 true
  const [incorrectSecureCode, setIncorrectSecureCode] = useState(false); // 실제와 입력 받은 보안코드가 일치하지 않을 때 true

  const handleOpen = () => setOpen(true); // 비밀번호 찾기를 눌렀을 때
  const handleClose = () => setOpen(false); // 비밀번호 찾기 닫았을 때
  const onChangeEmail = (e) => {
    // 사용자가 email 입력하는 event
    setEmail(e.target.value); // email을 사용자가 입력한 값으로. 사용자의 email 얻어옴
    console.log("onchangeEmail");
  };
  const onChangeEmailAddress = (e) => {
    // event
    setEmailAddress(e.target.value); // email의 주소를 사용자가 입력한 값으로. 사용자의 email 주소 얻어옴.
    console.log("onchangeEmailAddress");
  };
  const onChangeSecurityCode = (e) => {
    // 사용자가 보안 코드 입력하는 event
    setInputSecurityCode(e.target.value); // 사용자가 입력한 보안코드 가져옴
    console.log("onchangeSecurityCode");
  };
  const onChangePassword = (e) => {
    setPassword(e.target.value); // 사용자가 입력한 password 가져옴
    console.log("onchangePassword");
  };
  const onChangePasswordCheck = (e) => {
    setPasswordCheck(e.target.value); // 비밀번호 확인 부분
    console.log("onchangePasswordCheck");
  };
  const handleClickChangePassword = () => {
    // 비밀번호 변경 btn을 눌렀을 때
    const data = {
      email: email + "@" + emailAddress,
      pw: password,
    };

    if (password === "") {
      setEmptyPW(true); // pw가 비어있다
    } else {
      setEmptyPW(false); // pw에 뭔갈 썼다!
    }
    if (password === passwordCheck) {
      setCorrectPW(false);
      // 비밀번호 변경
      fetch("/changepw", {
        method: "post",
        headers: {
          "content-type": "application/json",
        },
        body: JSON.stringify(data),
      });
      alert("비밀번호 변경!");
      window.location.replace("/");
    } else {
      setCorrectPW(true);
    }
  };

  const handleClickSend = () => {
    // 보내기를 누른다.
    const data = {
      email: email + "@" + emailAddress,
    };
    console.log(email + "@" + emailAddress);
    if (email === "") {
      // email 입력 X
      if (emailAddress === "") {
        // 둘다 입력 X일 때
        setEmptyEmailAddress(true);
        setEmptyEmail(true);
      } else {
        // 이메일 주소만 입력했을 때
        setEmailCheck(false);
      }
    } else {
      // email 입력 O
      if (emailAddress === "") {
        // 이메일만 입력했을 때
        setEmailAddress(true);
      } else {
        // 모두 입력 했을 때
        setEmptyEmail(false);
        setEmptyEmailAddress(false);

        // 이메일 존재하는지 확인
        fetch("/isthereemail", {
          method: "post",
          headers: {
            "content-type": "application/json",
          },
          body: JSON.stringify(data),
        })
          .then((res) => res.json())
          .then((json) => {
            if (json.result === 1) {
              // DB에 존재하는 ID일 때
              setEmailCheck(true);

              // 해당 이메일로 보안코드 전송하기
              fetch("/sendemail", {
                method: "post",
                headers: {
                  "content-type": "application/json",
                },
                body: JSON.stringify(data),
              })
                .then((res) => res.json())
                .then((json) => {
                  setSecurityCode(json.number); // 실제 securitycode 설정
                });
            } else {
              // DB에 없는 ID일 때
              alert("존재하지 않는 ID입니다.");
            }
          });
      }
    }
  };

  const handleClickResend = () => {
    const data = {
      email: email + "@" + emailAddress,
    };

    // 이메일 다시 보내기
    fetch("/sendemail", {
      method: "post",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then((res) => res.json())
      .then((json) => {
        setSecurityCode(json.number);
      });
  };

  const handleClickSecureCodeCheck = () => {
    // 보안코드가 일치하면
    if (securityCode === inputSecurityCode) {
      setSecurityCheck(true);
    }
    // 일치하지 않으면
    else {
      setIncorrectSecureCode(true);
      alert("보안코드가 잘못 입력되었습니다.");
    }
  };

  return (
    <div>
      <button className="sub_btn" onClick={handleOpen}>
        비밀번호 찾기
      </button>
      <Modal open={open} onClose={handleClose}>
        <Box className="modal">
          <Box className="findPW">비밀번호찾기</Box>
          <span style={{ fontSize: "14px" }}>이메일 확인</span>
          <Stack
            direction={{ xs: "column", sm: "row" }}
            alignItems="center"
            spacing={{ xs: 0, sm: 4 }}
          >
            <Stack direction="row" alignItems="center" spacing={1}>
              <TextField
                disabled={emailCheck} // emailCheck가 true이면 textfield에 입력 못함. -> DB에 존재하는 email일 때 true
                id="email"
                type="text"
                error={emptyEmail}
                helperText={emptyEmail && "이메일을 입력하세요."}
                value={email}
                label="이메일"
                variant="outlined"
                size="small"
                margin="normal"
                onChange={onChangeEmail} // input 할 때마다 state 변하지 않도록 onChange 함수 안에 setState 함수
              />
              <span style={{ marginTop: 6 }}>@</span>
              <FormControl sx={{ width: 150 }} size="small">
                <InputLabel id="emailAdress" sx={{ marginTop: 1 }}>
                  이메일주소
                </InputLabel>
                <Select
                  disabled={emailCheck} // emailCheck가 true이면 입력 못함. -> DB에 존재하는 email일 때
                  className="select"
                  error={emptyEmailAddress}
                  labelId="emailAddress"
                  value={emailAddress}
                  name="emailAddress"
                  label="이메일 주소"
                  onChange={onChangeEmailAddress}
                  size="small"
                  sx={{ marginTop: 1 }}
                >
                  {EMAILADDRESS.map((emailAddress, idx) => {
                    return (
                      <MenuItem key={idx} value={emailAddress}>
                        {emailAddress}
                      </MenuItem>
                    );
                  })}
                </Select>
              </FormControl>
            </Stack>
            <Button
              disabled={emailCheck}
              variant="outlined"
              startIcon={<SendIcon />}
              size="small"
              className="send_btn"
              onClick={handleClickSend}
            >
              보내기
            </Button>
          </Stack>
          <Stack direction="row" alignItems="center" spacing={5} mb={2}>
            <TextField
              disabled={securityCheck}
              error={incorrectSecureCode}
              helperText={incorrectSecureCode && "보안코드를 확인하세요."}
              id="securityCode"
              type="text"
              value={inputSecurityCode}
              label="보안코드"
              variant="outlined"
              size="small"
              margin="normal"
              onChange={onChangeSecurityCode}
              sx={{ width: "40%" }}
              s
            />
            <Stack direction="row" alignItems="center" style={{ marginTop: 5 }}>
              <Button
                disabled={securityCheck}
                variant="outlined"
                size="small"
                onClick={handleClickSecureCodeCheck}
              >
                확인
              </Button>
              &nbsp;
              <Button
                disabled={securityCheck}
                variant="outlined"
                size="small"
                onClick={handleClickResend}
              >
                재전송
              </Button>
            </Stack>
          </Stack>
          <span style={{ fontSize: "14px" }}>비밀번호 변경</span>
          <Stack spacing={1} className="PW_Stack">
            <TextField
              disabled={!securityCheck} // securityCheck가 false이면 입력 못해 -> 보안코드 틀림
              id="password"
              error={emptyPW} // textfield에 잘못된 값이나 빈 값을 넣었을 때 error 발생. 빈 값 -> emptyPW=false -> error
              type="password"
              helperText={emptyPW && "비밀번호를 입력하세요."} // 어떤 이유로 에러가 발생했는지 이유 알려줌.
              value={password}
              label="비밀번호"
              variant="outlined"
              size="small"
              margin="normal"
              onChange={onChangePassword}
            />
            <TextField
              disabled={!securityCheck} // securityCheck가 false이면 입력 못해 -> 보안코드 틀림
              id="passwordCheck"
              error={incorrectPW}
              type="password"
              helperText={incorrectPW && "비밀번호가 다릅니다."}
              value={passwordCheck}
              label="비밀번호 확인"
              variant="outlined"
              size="small"
              margin="normal"
              onChange={onChangePasswordCheck}
            />
          </Stack>
          <Box className="btn_area" style={{ margin: "30px 0 10px 0" }}>
            <button className="btn" onClick={handleClickChangePassword}>
              변경
            </button>
          </Box>
        </Box>
      </Modal>
    </div>
  );
};

export default FindPW;
