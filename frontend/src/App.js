import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import PrivateRoute from "./Components/PrivateRoute";
import SignIn from "./pages/SignIn";
import SignUp from "./pages/SignUp";
import MyPage from "./pages/MyPage";
import Input from "./pages/Input";
import Result from "./pages/Result";
import Stats from "./pages/Stats";
import Admin from "./pages/Admin";
import GradReqChange from "./pages/GradReqChange";
import AddNewSemester from "./pages/AddNewSemster";
import CreateDataSet from "./pages/CreateDataSet";
import { createTheme, ThemeProvider } from "@mui/material";

const theme = createTheme({
  typography: {
    fontFamily: "'NanumSquare', 'SsangMun', sans-serif",
  },
});

const App = () => {
  return (
      <ThemeProvider theme={theme}>
        <Routes>
          <Route path="/" element={<Home />} exact />
          <Route path="/signin" element={<SignIn />} />
          <Route path="/signup" element={<SignUp />} />
          <Route element={<PrivateRoute />}>
            <Route path="/mypage" element={<MyPage />} />
            <Route path="/input" element={<Input />} />
            <Route path="/result" element={<Result />} />
            <Route path="/stats" element={<Stats />} />
            <Route path="/admin" element={<Admin />} />
            <Route path="/change" element={<GradReqChange />} />
            <Route path="/newsem" element={<AddNewSemester />} />
            <Route path="/dataset" element={<CreateDataSet />} />
          </Route>
        </Routes>
      </ThemeProvider>
  );
};

export default App;
