package cn.cupbread.mims.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@ApiModel(value = "Inventory库存类")
@Accessors(chain = true)
@Data
public class Inventory implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "产品id")
    private Long pId;

    @ApiModelProperty(value = "仓库id")
    private Long wId;

    @ApiModelProperty(value = "库存数量")
    private Long quantity;


}