package cn.cupbread.mims.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/5/28
 * @description :
 */

@ApiModel(value = "Product产品类")
@Accessors(chain = true)
@Data
public class Product implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "产品图片")
    private String img;

    @ApiModelProperty(value = "产品名称")
    private String name;

    @ApiModelProperty(value = "产品货号")
    private Integer itemNumber;

    @ApiModelProperty(value = "产品规格")
    private String specifications;

    @ApiModelProperty(value = "条形码")
    private String barCode;

    @ApiModelProperty(value = "计量单位")
    private Long unit;

    @ApiModelProperty(value = "销售价")
    private Double sales;

    @ApiModelProperty(value = "进货价")
    private Double purchase;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "产品分类id")
    private Long cid;

    @ApiModelProperty(value = "创建员工")
    private Long uid;

}
