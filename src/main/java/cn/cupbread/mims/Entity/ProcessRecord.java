package cn.cupbread.mims.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/5/31
 * @description :
 */

@ApiModel(value = "ProcessRecord加工记录类")
@Accessors(chain = true)
@Data
public class ProcessRecord implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "编号")
    private String serialNumber;

    @ApiModelProperty(value = "产品id")
    private Long pId;

    @ApiModelProperty(value = "仓库id")
    private Long wId;

    @ApiModelProperty(value = "数量")
    private Long quantity;

    @ApiModelProperty(value = "加工日期")
    private Date produceTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建用户")
    private Long uId;
}
