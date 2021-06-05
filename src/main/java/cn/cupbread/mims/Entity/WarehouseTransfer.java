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

@ApiModel(value = "WarehouseTransfer货物调度类")
@Accessors(chain = true)
@Data
public class WarehouseTransfer implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "操作用户")
    private Long uId;

    @ApiModelProperty(value = "拔入仓库")
    private Long inId;

    @ApiModelProperty(value = "排出仓库")
    private Long outId;

    @ApiModelProperty(value = "产品")
    private Long pId;

    @ApiModelProperty(value = "数量")
    private Long quantity;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "备注")
    private String remark;

}