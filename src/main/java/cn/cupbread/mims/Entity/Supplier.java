package cn.cupbread.mims.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@ApiModel(value = "Supplier供应商类")
@Accessors(chain = true)
@Data
public class Supplier {

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "联系人")
    private String linkman;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "邮编")
    private String pc;

    @ApiModelProperty(value = "传真")
    private String fax;

    @ApiModelProperty(value = "电话")
    private String tel;

    @ApiModelProperty(value = "EMAIL")
    private String email;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "网址")
    private String website;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "更新人id")
    private Date updateUid;

}
