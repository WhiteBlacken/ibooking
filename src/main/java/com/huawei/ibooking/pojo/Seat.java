package com.huawei.ibooking.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.font.StandardGlyphVector;

import java.io.Serializable;

/**
 * @Author qxy
 * @Date 2022/3/26 1:59
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Seat实体类",description = "")
@TableName("tbl_seat")
public class Seat implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private int id;

    @ApiModelProperty(value = "座位编号")
    private String seatNum;

    @ApiModelProperty(value = "studyRoomId")
    private int studyRoomId;

    @ApiModelProperty(value = "状态")
    private int status;

    @ApiModelProperty(value = "所属自习室")
    @TableField(exist = false)
    private StudyRoom studyRoom;
}
