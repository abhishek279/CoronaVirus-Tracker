package com.projects.coronavirustracker.controller;

import com.projects.coronavirustracker.model.LocationStats;
import com.projects.coronavirustracker.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
@Autowired
public CoronaVirusDataService coronaVirusDataService;
    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats=CoronaVirusDataService.getAllStats();
        int totalReportedCases= allStats.stream().mapToInt(stat-> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat-> stat. getDiffFromPreviosDay()).sum();
        model.addAttribute("locatioStats",allStats);
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalNewCases",totalNewCases);
        return "home";
    }
}
