package cn.cupbread.mims.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@ApiModel(value = "Warehouse仓库类")
@Accessors(chain = true)
@Data
public class Warehouse implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "仓库名称")
    private String name;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "负责人")
    private String personInCharge;

    @ApiModelProperty(value = "库存")
    private Long stock;

}
