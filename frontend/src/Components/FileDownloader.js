import React, { useState } from "react";

const FileDownloader = () => {
    const handleDownloadAll = () => {
        const folderPath = "../data/"; // 다운로드할 폴더 경로

        const downloadLink = document.createElement("a");
        downloadLink.href = folderPath;
        downloadLink.download = "TestDataSet.zip";
        downloadLink.click();
    };

    return (
        <div>
            <h2>Test DataSet Download</h2>
            <button onClick={handleDownloadAll}>download</button>
        </div>
    );
};

export default FileDownloader;