package com.example.nr.demo.controller;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/demo")
@EnableScheduling
public class DemoController {

    @Autowired
    MeterRegistry meterRegistry;

    @Scheduled(fixedRate = 60000)
    public long demoMethod() {

        long x = new Random().nextInt(10);
        List<String> branch_list = new ArrayList<String>();
        branch_list.add(0,"branch_0");
        branch_list.add(1,"branch_1");
        branch_list.add(2,"branch_2");
        branch_list.add(3,"branch_3");
        branch_list.add(4,"branch_4");
        branch_list.add(5,"branch_5");

        int y = new Random().nextInt(6);
        String currentTimestamp = String.valueOf(Instant.now().toEpochMilli());
        meterRegistry.counter("random.count2","branch",branch_list.get(y),"time_record",currentTimestamp).increment(x);
        System.out.println("For branch: "+branch_list.get(y)+" counter is "+x + " with time as "+currentTimestamp);
        return x;
    }

}
