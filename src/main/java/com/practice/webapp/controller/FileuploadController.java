package com.practice.webapp.controller;

 
import com.practice.webapp.entity.*;
import com.practice.webapp.entity.introduction.Fileupload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileuploadController {
	
    @RequestMapping(value = "/uploadxx", method = RequestMethod.GET)
    public String crunchifyDisplayForm() {
        return "fileupload";
    }
    
    @RequestMapping(value = "/savefilesxx", method = RequestMethod.POST)
    public String crunchifySave(
            @ModelAttribute("uploadForm") Fileupload uploadForm,
            Model map) throws IllegalStateException, IOException {
        String saveDirectory = "d:/crunchify/";
 
        List<MultipartFile> crunchifyFiles = uploadForm.getFiles();
 
        List<String> fileNames = new ArrayList<String>();
 
        if (null != crunchifyFiles && crunchifyFiles.size() > 0) {
            for (MultipartFile multipartFile : crunchifyFiles) {
 
                String fileName = multipartFile.getOriginalFilename();
                if (!"".equalsIgnoreCase(fileName)) {
                    // Handle file content - multipartFile.getInputStream()
                    multipartFile
                            .transferTo(new File(saveDirectory + fileName));
                    fileNames.add(fileName);
                }
            }
        }
 
        map.addAttribute("files", fileNames);
        return "fileuploadsuccess";
    }

}
