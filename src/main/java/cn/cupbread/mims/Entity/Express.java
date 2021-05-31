package cn.cupbread.mims.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(value = "Express快递类")
@Accessors(chain = true)
@Data
public class Express {

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "快递名称")
    private String name;


}
