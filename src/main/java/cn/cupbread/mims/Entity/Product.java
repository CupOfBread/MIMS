package cn.cupbread.mims.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/5/28
 * @description :
 */

@ApiModel(value = "Product产品类")
@Accessors(chain = true)
@Data
public class Product {

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "产品图片")
    private String img;

    @ApiModelProperty(value = "产品名称")
    private String name;

    @ApiModelProperty(value = "产品货号")
    private Integer itemNumber;

}
