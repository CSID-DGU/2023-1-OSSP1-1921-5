import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import SignIn from './pages/SignIn';
import SignUp from './pages/SignUp';
import MyPage from './pages/MyPage';
import Input from './pages/Input';
import Result from './pages/Result';
import Stats from './pages/Stats';
import Admin from './pages/Admin';
import CreateDataSet from './pages/CreateDataSet'
import './App.css';
import { createTheme, ThemeProvider } from '@mui/material';

const theme = createTheme({
  typography: {
    fontFamily: "'NanumSquare', 'SsangMun', sans-serif"
  }
})

const App = () => {
  return (
    <ThemeProvider theme={theme}>
      <div>
        <Routes>
          <Route path="/" element={<Home />} exact={true} />
          <Route path="/signin" element={<SignIn />} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="/mypage" element={<MyPage />} />
          <Route path="/input" element={<Input />} />
          <Route path="/result" element={<Result />} />
          <Route path="/stats" element={<Stats />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/dataset" element={<CreateDataSet />} />
        </Routes>
      </div>
    </ThemeProvider>
  );
};

export default App;
