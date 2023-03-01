package com.example.demo1;

import com.example.demo1.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Demo1ApplicationTest {

    private final Demo1Application demo1Application;

    @Autowired
    public Demo1ApplicationTest(Demo1Application demo1Application) {
        this.demo1Application = demo1Application;
    }

    @Test
    void contextLoad() {
        assertNotNull(demo1Application);
    }

}