package com.huawei.ibooking.test.booking.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

/**
 * @Author qxy
 * @Date 2022/3/31 16:51
 * @Version 1.0
 */

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
                features = {"classpath:features/booking/book_a_seat.feature"},
                glue = "com.huawei.ibooking.test.booking.defs",
                plugin = "html:target/cucumber/booking/test.html"
)
@ContextConfiguration
public class BookingTestRunner {
}
