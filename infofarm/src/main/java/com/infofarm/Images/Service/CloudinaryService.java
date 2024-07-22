package com.infofarm.Images.Service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    String[] uploadFile(MultipartFile file, String folderName);
    boolean deleteFile(String fileName);
}
