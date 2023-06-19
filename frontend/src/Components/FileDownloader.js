import React, { useState } from "react";

const FileDownloader = () => {
    const handleDownloadAll = async () => {
        try {
            const response = await fetch("/dataset/download"); // 백엔드의 '/download' 엔드포인트 호출
            if (response.ok) {
                const blob = await response.blob(); // 응답 데이터를 Blob으로 가져옴

                const downloadLink = document.createElement("a");
                const url = URL.createObjectURL(blob);
                downloadLink.href = url;
                downloadLink.download = "TestDataSet.zip";
                downloadLink.click();
                URL.revokeObjectURL(url);
            } else {
                console.error("다운로드 실패");
            }
        } catch (error) {
            console.error("다운로드 중 오류 발생:", error);
        }
    };

    return (
        <div>
            <h2>Test DataSet Download</h2>
            <button onClick={handleDownloadAll}>Download</button>
        </div>
    );
};

export default FileDownloader;
