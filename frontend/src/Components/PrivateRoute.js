import React from "react";
import { Outlet, Navigate } from "react-router-dom";

const PrivateRoute = () => {
    const userId = sessionStorage.getItem('userId')
    const isLoggedIn = !!userId; // null이면 false

    console.log(userId);
    console.log(!isLoggedIn);
    if (!isLoggedIn) {
        alert("로그인이 필요한 기능입니다.");
        return <Navigate to="/signin" />;
    }
    return <Outlet />;
};

export default PrivateRoute;