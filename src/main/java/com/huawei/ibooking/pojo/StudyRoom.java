package com.huawei.ibooking.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author qxy
 * @Date 2022/3/25 20:47
 * @Version 1.0
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tbl_study_room")
@ApiModel(value = "StudyRoom对象", description = "")
public class StudyRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private int id;

    @ApiModelProperty(value = "楼号")
    private String buildingNum;

    @ApiModelProperty(value = "教室号")
    private String classRoomNum;

    @ApiModelProperty(value = "当前状态 0-不可用 1-可用")
    private int status;

    @ApiModelProperty(value = "开始时间")
    private int beginHour;

    @ApiModelProperty(value = "结束时间")
    private int endHour;


}
