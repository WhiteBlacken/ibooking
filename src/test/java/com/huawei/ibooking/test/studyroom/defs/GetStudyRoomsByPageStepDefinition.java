package com.huawei.ibooking.test.studyroom.defs;


import com.huawei.ibooking.pojo.other.RespPageBean;
import com.huawei.ibooking.service.StudyRoomService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author qxy
 * @Date 2022/4/8 10:06
 * @Version 1.0
 */
public class GetStudyRoomsByPageStepDefinition {

    private int pageSize = 0;
    private int pageNum = 0;
    private RespPageBean respPageBean;

    @Autowired
    private StudyRoomService studyRoomService;

    @Given("page size is {int}")
    public void setPageSize(int pageSize){
        pageSize = pageSize;
    }

    @And("page number is {int}")
    public void setPageNum(int pageNum){
        pageNum = pageNum;
    }

    @When("I get all studyRooms")
    public void getAllStudyRooms(){
        System.out.println("studyroomService:"+studyRoomService);
        respPageBean = studyRoomService.getAllStudyRoom(pageNum,pageSize);
    }

    @Then("I should be given studyRooms of right num")
    public void getRightNumRooms(){
        Assert.assertTrue("i can see nothing", respPageBean!=null);
    }

}
