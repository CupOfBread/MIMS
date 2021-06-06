package cn.cupbread.mims.Service.Impl;

import cn.cupbread.mims.Component.RetResponse;
import cn.cupbread.mims.DAO.ProductInRecordDAO;
import cn.cupbread.mims.Entity.*;
import cn.cupbread.mims.Service.*;
import cn.hutool.core.date.DateUtil;
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
        if (product == null || supplier == null || warehouse == null) throw new RuntimeException("信息错误");

        //库存表新增记录
        boolean inventorySave = inventoryService.save(new Inventory()
                .setQuantity(record.getQuantity())
                .setPId(product.getId())
                .setWId(warehouse.getId()));
        //更新仓库库存
        warehouse.setStock(warehouse.getStock() + record.getQuantity());
        boolean warehouseSave = warehouseService.updateById(warehouse);
        //保存入库记录
        System.out.println(new Date());
        record.setCreateTime(new Date());
        record.setUId(user.getId());
        boolean productInSave = productInRecordService.save(record);

        return inventorySave && warehouseSave && productInSave;
    }
}
