package cn.cupbread.mims.DAO;

import cn.cupbread.mims.Entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/5/28
 * @description :
 */

@Repository
public interface ProductDAO extends BaseMapper<Product> {
}
