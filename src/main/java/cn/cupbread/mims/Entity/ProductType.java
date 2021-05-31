package cn.cupbread.mims.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(value = "ProductType产品类型类")
@Accessors(chain = true)
@Data
public class ProductType {

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "产品类型")
    private String name;


}
