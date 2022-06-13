package com.example.ghost_storage.Controllers;

import com.example.ghost_storage.Services.UserService;
import com.example.ghost_storage.Storage.FileRepo;
import com.example.ghost_storage.Storage.UserRepo;
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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
class MainController {
    private final FileRepo fileRepo;
    private final UserService userService;

    @Value("${upload.path}")
    private String uploadPath;

    public MainController(FileRepo fileRepo, UserService userService) {
        this.userService = userService;
        this.fileRepo = fileRepo;
    }

    @GetMapping("/")
    public String hello(){
        return "hellopage";
    }

    @GetMapping("/main")
    public String main(
            @RequestParam(defaultValue = "") String descFilter,
            @RequestParam(defaultValue = "") String nameFilter,
            @RequestParam(defaultValue = "") String codeNameFilter,
            @RequestParam(defaultValue = "") String okccodeFilter,
            @RequestParam(defaultValue = "") String okpdcodeFilter,
            @RequestParam(defaultValue = "") String adoptionDateFilter,
            @RequestParam(defaultValue = "") String introductionDateFilter,
            @RequestParam(defaultValue = "") String developerFilter,
            @RequestParam(defaultValue = "") String predecessorFilter,
            @RequestParam(defaultValue = "") String contentsFilter,
            @RequestParam(defaultValue = "") String levelOfAcceptanceFilter,
            @RequestParam(defaultValue = "") String changesFilter,
            @RequestParam(defaultValue = "") String statusFilter,
            @RequestParam(defaultValue = "") String referencesAmountFilter,
            Map<String, Object> model) {
//        Iterable<Data> messages = fileRepo.findByFileDescLikeAndNameLike(li(descFilter), li(nameFilter));
        Iterable<Data> messages =
                fileRepo.findByFileDescLikeAndNameLikeAndCodeNameLikeAndOkccodeLikeAndOkpdcodeLikeAndAdoptionDateLikeAndIntroductionDateLikeAndDeveloperLikeAndPredecessorLikeAndContentsLikeAndLevelOfAcceptanceLikeAndChangesLikeAndStatusLikeAndReferencesAmountLike(
                        li(descFilter),
                        li(nameFilter),
                        li(codeNameFilter),
                        li(okccodeFilter),
                        li(okpdcodeFilter),
                        li(adoptionDateFilter),
                        li(introductionDateFilter),
                        li(developerFilter),
                        li(predecessorFilter),
                        li(contentsFilter),
                        li(levelOfAcceptanceFilter),
                        li(changesFilter),
                        li(statusFilter),
                        li(referencesAmountFilter)
                );

        model.put("messages", messages);
        model.put("formAction", "/main");
        model.put("descFilter", descFilter);
        model.put("nameFilter", nameFilter);
        model.put("codeNameFilter", codeNameFilter);
        model.put("okccodeFilter", okccodeFilter);
        model.put("okpdcodeFilter", okpdcodeFilter);
        model.put("adoptionDateFilter", adoptionDateFilter);
        model.put("introductionDateFilter", introductionDateFilter);
        model.put("developerFilter", developerFilter);
        model.put("predecessorFilter", predecessorFilter);
        model.put("contentsFilter", contentsFilter);
        model.put("levelOfAcceptanceFilter", levelOfAcceptanceFilter);
        model.put("changesFilter", changesFilter);
        model.put("statusFilter", statusFilter);
        model.put("referencesAmountFilter", referencesAmountFilter);
        return "main";
    }

    private String li(String str) {
        if (str.equals("")) {
            return "%";
        }
        else {
            return "%" + str + "%";
        }
    }
}
