package com.huawei.ibooking.pojo.other;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 座位查询类
 *
 * @Author qxy
 * @Date 2022/3/26 17:16
 * @Version 1.0
 */

@Data
@Api(tags = "座位条件查询类")
public class SeatCondition {


    @ApiModelProperty("座位编号")
    private String seatNum;

    @ApiModelProperty(value = "状态")
    private int status;

    @ApiModelProperty(value = "所属教室编号")
    private String studyRoomNum;

    @ApiModelProperty(value = "所属楼编号")
    private String buildingNum;


}
