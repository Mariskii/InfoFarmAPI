package com.infofarm.Images.Implementation;

import com.cloudinary.Cloudinary;
import com.infofarm.Images.Service.CloudinaryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    @Resource
    private Cloudinary cloudinary;

    @Override
    public String[] uploadFile(MultipartFile file, String folderName) {
        try{
            HashMap<Object, Object> options = new HashMap<>();
            options.put("folder", folderName);
            Map uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
            String publicId = (String) uploadedFile.get("public_id");
            return new String[] {cloudinary.url().secure(true).generate(publicId),publicId};

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteFile(String publicId) {
        try {
            System.out.println(publicId);
            System.out.println(cloudinary.uploader().destroy(publicId, null));

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
