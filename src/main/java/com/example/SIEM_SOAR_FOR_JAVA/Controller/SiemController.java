package com.example.SIEM_SOAR_FOR_JAVA.Controller;

import com.example.SIEM_SOAR_FOR_JAVA.Model.AlertEntity;
import com.example.SIEM_SOAR_FOR_JAVA.Model.LogEntity;
import com.example.SIEM_SOAR_FOR_JAVA.Repo.AlertRepo;
import com.example.SIEM_SOAR_FOR_JAVA.Repo.LogRepo;
import com.example.SIEM_SOAR_FOR_JAVA.Service.SiemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class SiemController {
    private final SiemService siemService;
    private final LogRepo logRepo;
    private final AlertRepo alertRepo;

    @Autowired
    public SiemController(SiemService siemService, LogRepo logRepo, AlertRepo alertRepo) {
        this.siemService = siemService;
        this.logRepo = logRepo;
        this.alertRepo = alertRepo;
    }

    @PostMapping("/logs")
    public String GetLog(@RequestParam String ip, @RequestParam String bio) {
        LogEntity logEntity = new LogEntity();
        logEntity.setIp(ip);
        logEntity.setBio(bio);
        logEntity.setTime(LocalDateTime.now());
        siemService.SiemSystem(logEntity.getIp(), logEntity.getBio());
        return "redirect:/logs";
    }

    @GetMapping("/")
    public String indexPage(){
        return "index";
    }

    @GetMapping("/logs")
    public String FindAllLog(Model model){
        List<LogEntity> logs = logRepo.findAll();
        model.addAttribute("logs", logs);
        return "logs";
    }

    @GetMapping("/logs_form")
    public String ShowForm(){
        return "logs_form";
    }

    @GetMapping("/alerts")
    public String FindAllAlert(Model model){
        List<AlertEntity> alerts = alertRepo.findAll();
        model.addAttribute("alerts", alerts);
        return "alerts";
    }
}
