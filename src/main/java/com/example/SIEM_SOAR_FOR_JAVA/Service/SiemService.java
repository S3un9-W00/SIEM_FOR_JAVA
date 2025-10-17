package com.example.SIEM_SOAR_FOR_JAVA.Service;

import com.example.SIEM_SOAR_FOR_JAVA.Model.AlertEntity;
import com.example.SIEM_SOAR_FOR_JAVA.Model.LogEntity;
import com.example.SIEM_SOAR_FOR_JAVA.Repo.AlertRepo;
import com.example.SIEM_SOAR_FOR_JAVA.Repo.LogRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SiemService {
    private final LogRepo logRepo;
    private final AlertRepo alertRepo;

    @Autowired
    public SiemService(LogRepo logRepo, AlertRepo alertRepo) {
        this.logRepo = logRepo;
        this.alertRepo = alertRepo;
    }

    public void SiemSystem(String ip, String bio) {
        String[] words = {"or", "1=1", "<script>", "</script>"};
        boolean issave = false;

        AlertEntity alert = new AlertEntity();
        LogEntity log = new LogEntity();
        log.setIp(ip);
        log.setBio(bio);
        log.setTime(LocalDateTime.now());
        logRepo.save(log);

        for(String word : words){
            if (log.getBio().toLowerCase().contains(word)){
                issave = true;
                alert.setIp(ip);
                alert.setBio("💀 의심 단어 감지 : " + bio);
                alert.setTime(LocalDateTime.now());
                alertRepo.save(alert);
                System.out.println("alert 저장 성공");
            }
            else if (!log.getBio().toLowerCase().contains(word)) {
                continue;
            }
        }

        if(bio.contains("failed") && issave == false) {
            alert.setIp(ip);
            alert.setBio("⚠️ 로그인 실패 감지 : " + bio);
            alert.setTime(LocalDateTime.now());
            alertRepo.save(alert);
            System.out.println("alert 저장 성공");
        }


    }
}
