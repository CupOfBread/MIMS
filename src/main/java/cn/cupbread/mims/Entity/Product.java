package cn.cupbread.mims.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/5/28
 * @description :
 */

@Accessors(chain = true)
@Data
public class Product {

    @TableId(type = IdType.AUTO)
    private Long id;

}
