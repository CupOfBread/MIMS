package cn.cupbread.mims.Service.Impl;

import cn.cupbread.mims.DAO.WarehouseTransferDAO;
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
 * @date : 2021/6/9
 * @description :
 */

@Service
public class WarehouseTransferServiceImpl extends ServiceImpl<WarehouseTransferDAO, WarehouseTransfer> implements WarehouseTransferService {

    @Autowired
    private WarehouseTransferService warehouseTransferService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private InventoryService inventoryService;

    @Override
    @Transactional
    public Boolean warehouseTransfer(WarehouseTransfer transferRecord) {
        User user = userService.getById(transferRecord.getUId());
        Warehouse warehouseOut = warehouseService.getById(transferRecord.getOutId());
        Warehouse warehouseIn = warehouseService.getById(transferRecord.getInId());
        Product product = productService.getById(transferRecord.getPId());
        if (user == null || warehouseOut == null || warehouseIn == null || product == null)
            throw new NullPointerException("信息错误");
        QueryWrapper<Inventory> inventoryQueryWrapper = new QueryWrapper<>();
        inventoryQueryWrapper.eq("p_id", product.getId()).eq("w_id", warehouseOut.getId());
        Inventory inventoryOut = inventoryService.getOne(inventoryQueryWrapper);
        Inventory inventoryIn = inventoryService.getOne(inventoryQueryWrapper);
        if (transferRecord.getQuantity() > inventoryOut.getQuantity())
            throw new NullPointerException("调度数量大于仓储数量！");
        inventoryOut.setQuantity(inventoryOut.getQuantity() - transferRecord.getQuantity());
        boolean flag = true;
        if (inventoryIn == null) {
            flag = flag & inventoryService.updateById(inventoryOut);
            inventoryIn = new Inventory()
                    .setPId(product.getId())
                    .setWId(warehouseIn.getId())
                    .setQuantity(inventoryOut.getQuantity());
            flag = flag & inventoryService.save(inventoryIn);
        } else {
            inventoryIn.setQuantity(inventoryIn.getQuantity() + transferRecord.getQuantity());
            flag = flag & inventoryService.updateById(inventoryIn);
        }
        flag = flag & inventoryService.updateById(inventoryOut);
        warehouseOut.setStock(warehouseOut.getStock() - transferRecord.getQuantity());
        warehouseIn.setStock(warehouseOut.getStock() + transferRecord.getQuantity());
        flag = flag & warehouseService.updateById(warehouseIn);
        flag = flag & warehouseService.updateById(warehouseOut);
        flag = flag & warehouseTransferService.save(transferRecord);
        if (!flag) throw new DatabaseOperationException("数据库操作错误");
        return true;
    }
}
