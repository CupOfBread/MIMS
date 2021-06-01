package cn.cupbread.mims.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@ApiModel(value = "Category产品分类类")
@Accessors(chain = true)
@Data
public class Category implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "分类名称")
    private String name;


}