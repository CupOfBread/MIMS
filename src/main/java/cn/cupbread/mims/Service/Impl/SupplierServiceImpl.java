package cn.cupbread.mims.Service.Impl;

import cn.cupbread.mims.DAO.SupplierDAO;
import cn.cupbread.mims.Entity.Supplier;
import cn.cupbread.mims.Service.SupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/4
 * @description :
 */

@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierDAO, Supplier> implements SupplierService {
}
