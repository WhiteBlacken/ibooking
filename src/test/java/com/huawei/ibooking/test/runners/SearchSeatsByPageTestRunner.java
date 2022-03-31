package com.huawei.ibooking.test.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * @Author qxy
 * @Date 2022/3/31 16:51
 * @Version 1.0
 */

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
                features = {"classpath:features/seat/search_seats_by_page.feature"},
                glue = "com.huawei.ibooking.test.defs",
                plugin = "html:target/cucumber/test.html"
)
public class SearchSeatsByPageTestRunner {
}
