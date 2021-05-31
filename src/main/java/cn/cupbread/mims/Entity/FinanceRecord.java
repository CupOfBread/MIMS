package cn.cupbread.mims.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@ApiModel(value = "FinanceRecord财务记录类")
@Accessors(chain = true)
@Data
public class FinanceRecord {

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建用户")
    private Long uId;

    @ApiModelProperty(value = "银行")
    private Long bankId;

    @ApiModelProperty(value = "分类")
    private Long cId;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "收入支出类型")
    private String type;

    @ApiModelProperty(value = "金额")
    private Double money;

    @ApiModelProperty(value = "日期时间")
    private Date dateTime;

    @ApiModelProperty(value = "经办人")
    private Long attnId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "备注")
    private String remark;


}