package com.packages.keyvaluestorechord.controller;


import com.packages.keyvaluestorechord.model.ProcessAttr;
import com.packages.keyvaluestorechord.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/process")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @PostMapping("/addProcess")
    public ProcessAttr createNewProcess (@RequestParam int startRange, @RequestParam int endRange) {
        return this.processService.addNewProcess(startRange, endRange);
    }
}
