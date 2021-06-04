package cn.cupbread.mims.Service.Impl;

import cn.cupbread.mims.DAO.ProductDAO;
import cn.cupbread.mims.Entity.Product;
import cn.cupbread.mims.Service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/4
 * @description :
 */

@Service
public class ProductServiceImpl extends ServiceImpl<ProductDAO, Product> implements ProductService {
}
