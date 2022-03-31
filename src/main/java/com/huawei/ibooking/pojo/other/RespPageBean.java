package com.huawei.ibooking.pojo.other;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页返回实体类
 *
 * @Author qxy
 * @Date 2022/3/26 0:39
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "分页返回实体类")
public class RespPageBean {

    @ApiModelProperty(value = "返回的数量")
    private long total;

    @ApiModelProperty(value = "返回的内容")
    private List<?> data;
}
