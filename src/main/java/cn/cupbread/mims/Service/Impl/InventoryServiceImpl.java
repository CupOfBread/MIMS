package cn.cupbread.mims.Service.Impl;

import cn.cupbread.mims.DAO.InventoryDAO;
import cn.cupbread.mims.Entity.Inventory;
import cn.cupbread.mims.Service.InventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/4
 * @description :
 */

@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryDAO, Inventory> implements InventoryService {
}
