package com.max.dto;

import java.io.Serializable;

/**
 * @author Max
 * @description
 * @date 2025/2/23 16:38
 */
public class IOTBinFile implements Serializable {
    private String fileName;
    private byte[] fileContent;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }




}
