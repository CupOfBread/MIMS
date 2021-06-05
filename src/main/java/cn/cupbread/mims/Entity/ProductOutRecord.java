package cn.cupbread.mims.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "ProductOutRecord出库记录类")
@Accessors(chain = true)
@Data
public class ProductOutRecord implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "序列号")
    private String serialNumber;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "产品id")
    private Long pId;

    @ApiModelProperty(value = "创建用户")
    private Long uId;

    @ApiModelProperty(value = "仓库id")
    private Long wId;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "发货日期")
    private Date shipTime;

    @ApiModelProperty(value = "折扣")
    private Double discounts;

    @ApiModelProperty(value = "数量")
    private Long quantity;

    @ApiModelProperty(value = "金额")
    private Double amount;

    @ApiModelProperty(value = "销售成本")
    private Double cost;

    @ApiModelProperty(value = "产品数据")
    private String productData;

    @ApiModelProperty(value = "快递")
    private Long expressId;

    @ApiModelProperty(value = "快递单号")
    private String expressNum;

    @ApiModelProperty(value = "地址")
    private String expressAddr;

    @ApiModelProperty(value = "备注")
    private String remark;

}