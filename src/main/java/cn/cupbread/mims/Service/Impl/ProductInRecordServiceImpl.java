package cn.cupbread.mims.Service.Impl;

import cn.cupbread.mims.DAO.ProductInRecordDAO;
import cn.cupbread.mims.Entity.*;
import cn.cupbread.mims.Exception.DatabaseOperationException;
import cn.cupbread.mims.Service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/4
 * @description :
 */

@Service
public class ProductInRecordServiceImpl extends ServiceImpl<ProductInRecordDAO, ProductInRecord> implements ProductInRecordService {

    @Autowired
    private ProductInRecordService productInRecordService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private InventoryService inventoryService;

    @Override
    @Transactional
    public Boolean productIn(ProductInRecord record, Long uId) {
        Product product = productService.getById(record.getPId());
        Supplier supplier = supplierService.getById(record.getSId());
        Warehouse warehouse = warehouseService.getById(record.getWId());
        User user = userService.getById(uId);
        if (product == null || supplier == null || warehouse == null || user == null)
            throw new RuntimeException("信息错误");

        //库存表新增记录
        QueryWrapper<Inventory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("p_id", product.getId()).eq("w_id", warehouse.getId());
        Inventory inventory = inventoryService.getOne(queryWrapper);
        //库存表内是否存在该产品的库存
        boolean inventorySave = false;
        if (inventory == null) {
            inventorySave = inventoryService.save(new Inventory()
                    .setQuantity(record.getQuantity())
                    .setPId(product.getId())
                    .setWId(warehouse.getId()));
        } else {
            inventory.setQuantity(inventory.getQuantity() + record.getQuantity());
            inventorySave = inventoryService.updateById(inventory);
        }

        //更新仓库库存
        warehouse.setStock(warehouse.getStock() + record.getQuantity());
        boolean warehouseSave = warehouseService.updateById(warehouse);
        //保存入库记录
        record.setCreateTime(new Date());
        record.setUId(user.getId());
        boolean productInSave = productInRecordService.save(record);

        if (!inventorySave || !warehouseSave || !productInSave) throw new DatabaseOperationException();
        return true;
    }
}
