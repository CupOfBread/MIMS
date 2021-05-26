package cn.cupbread.mims.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author : CupOfBread
 * @version : 0.1.0
 * @date : 2021/05/24
 * @description :
 */

@Accessors(chain = true)
@Data
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;



}
