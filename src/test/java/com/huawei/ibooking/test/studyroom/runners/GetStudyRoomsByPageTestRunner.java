package com.huawei.ibooking.test.studyroom.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

/**
 * @Author qxy
 * @Date 2022/4/8 10:04
 * @Version 1.0
 */

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
        features = {"classpath:features/studyroom/get_all_studyroom_by_page.feature"},
        glue = "com.huawei.ibooking.test.studyroom.defs",
        plugin = "html:target/cucumber/test.html"
)
@ContextConfiguration
public class GetStudyRoomsByPageTestRunner {
}
