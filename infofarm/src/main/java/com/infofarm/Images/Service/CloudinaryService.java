package com.infofarm.Images.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {
    String[] uploadFile(MultipartFile file, String folderName);
    boolean deleteFile(String fileName) throws IOException;
}
