package com.example.ghost_storage.Controllers;

import com.example.ghost_storage.Storage.FileRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import com.example.ghost_storage.Model.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class MyDocumentsController {
    private final FileRepo fileRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public MyDocumentsController(FileRepo fileRepo) {
        this.fileRepo = fileRepo;
    }

    @GetMapping("/my_docs")
    public String my_docs(
            @RequestParam(required = false, defaultValue = "") String descFilter,
            @AuthenticationPrincipal User user,
            String nameFilter,
            Map<String, Object> model) {
        Iterable<Data> messages;
        if (descFilter != null && !descFilter.isEmpty()) {
            messages = fileRepo.findByAuthorAndFileDescLike(user, '%' + descFilter + '%');
        } else if (nameFilter != null && !nameFilter.isEmpty()) {
            messages = fileRepo.findByAuthorAndNameLike(user, '%' + nameFilter + '%');
        } else {
            messages = fileRepo.findByAuthor(user);
        }

        model.put("messages", messages);
        model.put("formAction", "/my_docs");
        model.put("descFilter", descFilter);
        model.put("nameFilter", nameFilter);

        return "my_docs";
    }

    @PostMapping("/my_docs")
    public String add(
            @RequestParam("file") MultipartFile uploadData,
            @RequestParam String name,
            @RequestParam String fileDesc,
            @AuthenticationPrincipal User user, Map<String, Object> model) throws IOException {
        if (uploadData != null && !uploadData.getOriginalFilename().isEmpty()) {
            Data data = new Data(name, fileDesc, user);
            File uploadFolder = new File(uploadPath);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + uploadData.getOriginalFilename();
            uploadData.transferTo(new File(uploadPath + "/" + resultFileName));
            data.setFilename(resultFileName);
            fileRepo.save(data);
        }
        model.put("messages", fileRepo.findByAuthor(user));
        return "my_docs";
    }

    @GetMapping("/delete/{dataID}")
    public String delete(
            @PathVariable String dataID,
            @AuthenticationPrincipal User user,
            Map<String, Object> model) throws FileNotFoundException {
        List<Data> dataList = fileRepo.findById(Integer.parseInt(dataID));
        if (dataList.isEmpty()) {
            return "redirect:/my_docs";
        }
        Data data = dataList.get(0);
        boolean userIsAuthor = user.getId().equals(data.getAuthor().getId());
        if (!user.isAdmin() && !userIsAuthor) {
            return "redirect:/my_docs";
        }
        File file = new File(uploadPath + "/" + data.getFilename());
        if (!file.delete()) throw new FileNotFoundException();
        fileRepo.delete(data);

        // если админ удалил не свой файл, то редирект в мэйн
        if (user.isAdmin() && !userIsAuthor) {
            model.put("messages", fileRepo.findAll());
            return "redirect:/main";
        } else {
            model.put("messages", fileRepo.findByAuthor(user));
            return "redirect:/my_docs";
        }
    }
}
