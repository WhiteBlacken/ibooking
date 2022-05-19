package com.huawei.ibooking.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author qxy
 * @Date 2022/5/6 9:35
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "预约记录实体类",description = "")
@TableName("tbl_booking")
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private int id;

    @ApiModelProperty(value = "座位id")
    private int seatId;

    @ApiModelProperty(value = "学生id")
    private int stuId;

    @ApiModelProperty(value = "类型 1-预约 2-临时抢座")
    private int type;

    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH", timezone = "Asia/Shanghai")
    private LocalDateTime beginDate;

    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH", timezone = "Asia/Shanghai")
    private LocalDateTime endDate;

    public Booking(int seatId, int stuId,int type) {
        this.seatId = seatId;
        this.stuId = stuId;
        this.type = type;
    }
}