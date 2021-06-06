package cn.cupbread.mims.Controller;

import cn.cupbread.mims.Component.RetResponse;
import cn.cupbread.mims.Entity.Inventory;
import cn.cupbread.mims.Entity.Warehouse;
import cn.cupbread.mims.Service.InventoryService;
import cn.cupbread.mims.Service.WarehouseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/5
 * @description :
 */

@RequestMapping("/warehouse")
@Api(tags = {"仓库控制类"})
@RestController
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private InventoryService inventoryService;


    @ApiOperation("获取仓库-通过id")
    @GetMapping("/id")
    public RetResponse getWarehouseById(Long id) {
        return new RetResponse().makeOKRsp(200, "SUCCESS", warehouseService.getById(id));
    }

    @ApiOperation("获取所有仓库")
    @GetMapping("/page")
    public RetResponse getWarehousePage(@ApiParam(value = "当前页", example = "1", required = true) Long current,
                                        @ApiParam(value = "每页大小", example = "10", required = true) Long size) {
        Page<Warehouse> page = new Page<>(current, size);
        return new RetResponse().makeOKRsp(200, "SUCCESS", warehouseService.page(page));
    }

    @ApiOperation("更新仓库")
    @PostMapping("/update")
    public RetResponse updateWarehouse(Warehouse warehouse) {
        if (warehouseService.updateById(warehouse)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("新增仓库")
    @PostMapping("/add")
    public RetResponse addWarehouse(Warehouse warehouse) {
        if (warehouseService.save(warehouse)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("删除仓库")
    @PostMapping("/del")
    public RetResponse delWarehouse(Long id) {
        QueryWrapper<Inventory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("w_id", id);
        if (inventoryService.count(queryWrapper) > 0) {
            return new RetResponse().makeErrRsp(400, "已有产品使用该仓库，无法删除！");
        } else {
            if (warehouseService.removeById(id)) {
                return new RetResponse().makeOKRsp(200, "SUCCESS");
            } else {
                return new RetResponse().makeErrRsp(400, "FAIL");
            }
        }
    }
}
