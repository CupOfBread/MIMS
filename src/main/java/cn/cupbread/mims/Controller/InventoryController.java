package cn.cupbread.mims.Controller;

import cn.cupbread.mims.Component.RetResponse;
import cn.cupbread.mims.Entity.Inventory;
import cn.cupbread.mims.Entity.Product;
import cn.cupbread.mims.Entity.Warehouse;
import cn.cupbread.mims.Service.InventoryService;
import cn.cupbread.mims.Service.ProductService;
import cn.cupbread.mims.Service.WarehouseService;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/5
 * @description :
 */

@RequestMapping("/inventory")
@Api(tags = {"库存控制类"})
@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private WarehouseService warehouseService;


    @ApiOperation("获取库存-通过id")
    @GetMapping("/id")
    public RetResponse getInventoryById(Long id) {
        Inventory inventory = inventoryService.getById(id);
        JSONObject res = JSONUtil.createObj();
        res.putOpt("inventory", inventory);
        res.putOpt("warehouse", warehouseService.getById(inventory.getWId()));
        res.putOpt("product", productService.getById(inventory.getPId()));
        return new RetResponse().makeOKRsp(200, "SUCCESS", res);
    }

    @ApiOperation("获取所有库存")
    @GetMapping("/page")
    public RetResponse getInventoryPage(@ApiParam(value = "当前页", example = "1", required = true) Long current,
                                        @ApiParam(value = "每页大小", example = "10", required = true) Long size) {
        Page<Inventory> page = new Page<>(current, size);
        IPage<Inventory> inventoryPage = inventoryService.page(page);
        List<Inventory> inventoryList = inventoryPage.getRecords();
        if (inventoryList.size() == 0) return new RetResponse().makeOKRsp(200, null);
        Set<Long> productId = new HashSet<>();
        Set<Long> warehouseId = new HashSet<>();
        for (Inventory inventory : inventoryList) {
            productId.add(inventory.getPId());
            warehouseId.add(inventory.getWId());
        }
        List<Product> productList = productService.listByIds(productId);
        List<Warehouse> warehouseList = warehouseService.listByIds(warehouseId);
        JSONObject res = JSONUtil.createObj();
        res.putOpt("inventoryList", inventoryList);
        res.putOpt("productList", productList);
        res.putOpt("warehouseList", warehouseList);
        res.putOpt("total", inventoryPage.getTotal());

        return new RetResponse().makeOKRsp(200, "SUCCESS", res);
    }

}
