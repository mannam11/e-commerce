package com.store.ezbuy.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    public String uploadFile(MultipartFile file) throws IOException;

    public byte[] getFileContent(String fileName) throws IOException;

    public boolean deleteFile(String fileName);

    public String getContentType(String imageName);

}
