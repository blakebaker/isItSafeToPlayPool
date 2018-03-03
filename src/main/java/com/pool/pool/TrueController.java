package com.pool.pool;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrueController {

    @GetMapping("/")
    private String returnTrue() {
        return "Yes go play pool!!";
    }
}
