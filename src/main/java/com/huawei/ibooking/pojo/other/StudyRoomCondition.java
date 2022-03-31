package com.huawei.ibooking.pojo.other;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 自习室的查询类
 *
 * @Author qxy
 * @Date 2022/3/25 22:51
 * @Version 1.0
 */

@Data
@Api(tags = "自习室查询条件类")
public class StudyRoomCondition {

    @ApiModelProperty(value = "状态")
    private int status;

    @ApiModelProperty(value = "楼号")
    private String buildingNum;

    @ApiModelProperty(value = "教室号")
    private String classRoomNum;

    //只有可用时，选择时间才有意义
    @ApiModelProperty(value = "是否可用")
    private Boolean isValid = Boolean.FALSE;

    @ApiModelProperty(value = "开始时间")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private String beginDate;

    @ApiModelProperty(value = "结束时间")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private String endDate;
}
