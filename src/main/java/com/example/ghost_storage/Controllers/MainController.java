package com.example.ghost_storage.Controllers;

import com.example.ghost_storage.Storage.FileRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import com.example.ghost_storage.Model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;


@Controller
class MainController {
    private final FileRepo fileRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public MainController(FileRepo fileRepo) {
        this.fileRepo = fileRepo;
    }

    @GetMapping("/")
    public String hello(){
        return "hellopage";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String descFilter, String nameFilter, Map<String, Object> model) {
        Iterable<Data> messages;
        if (descFilter != null && !descFilter.isEmpty()) {
            messages = fileRepo.findByFileDescLike('%' + descFilter + '%');
        } else if (nameFilter != null && !nameFilter.isEmpty()) {
            messages = fileRepo.findByNameLike('%' + nameFilter + '%');
        } else {
            messages = fileRepo.findAll();
        }

        model.put("messages", messages);
        model.put("formAction", "/main");
        model.put("descFilter", descFilter);
        model.put("nameFilter", nameFilter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @RequestParam("file") MultipartFile uploadData,
            @RequestParam String name,
            @RequestParam String fileDesc,
            @AuthenticationPrincipal User user, Map<String, Object> model) throws IOException {
        Data data = new Data(name, fileDesc, user);
        if (uploadData != null && !uploadData.getOriginalFilename().isEmpty()) {
            File uploadFolder = new File(uploadPath);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + uploadData.getOriginalFilename();
            uploadData.transferTo(new File(uploadPath + "/" + resultFileName));
            data.setFilename(resultFileName);
        }
        fileRepo.save(data);
        Iterable<Data> messages = fileRepo.findAll();
        model.put("messages", messages);
        return "main";
    }
}
