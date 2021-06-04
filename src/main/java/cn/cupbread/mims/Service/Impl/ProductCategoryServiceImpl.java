package cn.cupbread.mims.Service.Impl;

import cn.cupbread.mims.DAO.ProductCategoryDAO;
import cn.cupbread.mims.Entity.ProductCategory;
import cn.cupbread.mims.Service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/4
 * @description :
 */

@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryDAO, ProductCategory> implements ProductCategoryService {
}
