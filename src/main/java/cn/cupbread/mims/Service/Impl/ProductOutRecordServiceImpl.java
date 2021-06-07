package cn.cupbread.mims.Service.Impl;

import cn.cupbread.mims.DAO.ProductOutRecordDAO;
import cn.cupbread.mims.Entity.*;
import cn.cupbread.mims.Exception.DatabaseOperationException;
import cn.cupbread.mims.Service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/4
 * @description :
 */

@Service
public class ProductOutRecordServiceImpl extends ServiceImpl<ProductOutRecordDAO, ProductOutRecord> implements ProductOutRecordService {

    @Autowired
    private ProductOutRecordService productOutRecordService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private InventoryService inventoryService;

    @Override
    @Transactional
    public Boolean productOut(ProductOutRecord record, Long uId) {
        Product product = productService.getById(record.getPId());
        Warehouse warehouse = warehouseService.getById(record.getWId());
        User user = userService.getById(uId);
        QueryWrapper<Inventory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("p_id", product.getId()).eq("w_id", warehouse.getId());
        Inventory inventory = inventoryService.getOne(queryWrapper);
        if (user == null || inventory == null) throw new RuntimeException("信息错误");

        //更新库存表
        inventory.setQuantity(inventory.getQuantity() - record.getQuantity());
        boolean inventorySave = inventoryService.updateById(inventory);
        //更新仓库库存
        warehouse.setStock(warehouse.getStock() - record.getQuantity());
        boolean warehouseSave = warehouseService.updateById(warehouse);
        //新增出库记录
        boolean productOutSave = productOutRecordService.save(record);

        if (!inventorySave || !warehouseSave || !productOutSave) throw new DatabaseOperationException();
        return true;
    }
}
