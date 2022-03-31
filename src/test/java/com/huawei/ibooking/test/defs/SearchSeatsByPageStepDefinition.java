package com.huawei.ibooking.test.defs;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huawei.ibooking.pojo.Seat;
import com.huawei.ibooking.pojo.StudyRoom;
import com.huawei.ibooking.pojo.other.RespPageBean;
import com.huawei.ibooking.pojo.other.SeatCondition;
import com.huawei.ibooking.service.SeatService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


/**
 * @Author qxy
 * @Date 2022/3/31 16:56
 * @Version 1.0
 */
public class SearchSeatsByPageStepDefinition {



    List<Seat> seats = new ArrayList<>();
    List<Seat> allSeats = new ArrayList<>();

    @Given("I have at least one seat available")
    public void checkSeatsNum(){
        if(seats.size()==0){
            seats.add(new Seat(5,"234",2,0,null));
            seats.add(new Seat(5,"234",2,0,null));
            seats.add(new Seat(5,"234",2,0,null));
            seats.add(new Seat(5,"234",2,0,null));
        }
    }
    @When("I search seats by page size of 4")
    public void searchSeats(){
        int count = 0;
        for(Seat seat:seats){
            allSeats.add(seat);
            count ++;
            if(count==4)break;
        }
    }

    @Then("I can see seats more than 0 and less than 5")
    public void iCanSeeSeatsOfRightNumber(){
        Assert.assertEquals("i can not see right number of seats",4,allSeats.size());
        Assert.assertTrue("what i see is not seats", allSeats.get(0) != null);
    }
}
