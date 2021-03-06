package cn.cupbread.mims.Service;

import cn.cupbread.mims.Entity.ProductInRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/2
 * @description :
 */

public interface ProductInRecordService extends IService<ProductInRecord> {

    // 产品入库
    Boolean productIn(ProductInRecord record, Long uId);
}
