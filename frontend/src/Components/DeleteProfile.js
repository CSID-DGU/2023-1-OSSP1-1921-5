import { React, useState } from 'react'
import './FindPW.css';

const handleDeleteProfile = () => {
    
    const email = sessionStorage.getItem('userId');

    if (window.confirm('확인을 누르면 회원 정보가 삭제됩니다.')) {
        console.log(email);
        fetch("/deletepf", {
            method: 'post',
            headers: {
              "content-type": "application/json",
            },
            body: JSON.stringify({ email }),
        })
        .then(() => {
            sessionStorage.clear()
            alert('탈퇴가 완료되었습니다.');
            window.location.replace('/');
        })
        
    } else {
      return;
    }
  };

const DeleteProfile = () => {
    return(
        <button className="sub_btn" onClick={handleDeleteProfile }>탈퇴하기</button>
    );
};

export default DeleteProfile