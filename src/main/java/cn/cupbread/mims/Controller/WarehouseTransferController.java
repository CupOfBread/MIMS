package cn.cupbread.mims.Controller;

import cn.cupbread.mims.Component.RetResponse;
import cn.cupbread.mims.Entity.Product;
import cn.cupbread.mims.Entity.User;
import cn.cupbread.mims.Entity.Warehouse;
import cn.cupbread.mims.Entity.WarehouseTransfer;
import cn.cupbread.mims.Service.ProductService;
import cn.cupbread.mims.Service.UserService;
import cn.cupbread.mims.Service.WarehouseService;
import cn.cupbread.mims.Service.WarehouseTransferService;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/10
 * @description :
 */

@RequestMapping("/warehouse/t")
@Api(tags = {"仓库调度控制类"})
@RestController
public class WarehouseTransferController {

    @Autowired
    private WarehouseTransferService warehouseTransferService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private WarehouseService warehouseService;


    @ApiOperation("获取仓库调度-通过id")
    @GetMapping("/id")
    public RetResponse getWarehouseTransferById(Long id) {
        return new RetResponse().makeOKRsp(200, "SUCCESS", warehouseTransferService.getById(id));
    }

    @ApiOperation("获取所有仓库调度")
    @GetMapping("/page")
    public RetResponse getWarehouseTransferPage(@ApiParam(value = "当前页", example = "1", required = true) Long current,
                                                @ApiParam(value = "每页大小", example = "10", required = true) Long size,
                                                @ApiParam(value = "开始时间", example = "2021-6-4", required = true) Date startTime,
                                                @ApiParam(value = "结束时间", example = "2021-6-4", required = true) Date endTime) {
        Page<WarehouseTransfer> page = new Page<>(current, size);
        QueryWrapper<WarehouseTransfer> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("create_time", startTime, endTime).orderByDesc("id");
        IPage<WarehouseTransfer> iPage = warehouseTransferService.page(page, queryWrapper);
        List<WarehouseTransfer> warehouseTransferList = iPage.getRecords();
        if (warehouseTransferList.size() == 0) return new RetResponse().makeOKRsp(200, "NULL");
        Set<Long> uId = new HashSet<>();
        Set<Long> pId = new HashSet<>();
        Set<Long> inId = new HashSet<>();
        Set<Long> outId = new HashSet<>();
        for (WarehouseTransfer w : warehouseTransferList) {
            uId.add(w.getUId());
            pId.add(w.getPId());
            inId.add(w.getInId());
            outId.add(w.getOutId());
        }
        List<User> userList = userService.listByIds(uId);
        List<Product> productList = productService.listByIds(pId);
        List<Warehouse> WarehouseIn = warehouseService.listByIds(inId);
        List<Warehouse> WarehouseOut = warehouseService.listByIds(outId);
        JSONObject res = JSONUtil.createObj();
        res.putOpt("userList", userList);
        res.putOpt("productList", productList);
        res.putOpt("WarehouseIn", WarehouseIn);
        res.putOpt("WarehouseOut", WarehouseOut);
        res.putOpt("total", iPage.getTotal());


        return new RetResponse().makeOKRsp(200, "SUCCESS", res);
    }


    @ApiOperation("新增仓库调度")
    @PostMapping("/add")
    public RetResponse addWarehouseTransfer(WarehouseTransfer warehouseTransfer) {
        if (warehouseTransferService.warehouseTransfer(warehouseTransfer)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("删除仓库调度")
    @PostMapping("/del")
    public RetResponse delWarehouseTransfer(Long id) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("warehouseTransfer", id);
        List<Product> productList = productService.list(queryWrapper);
        if (productList.size() > 0) {
            return new RetResponse().makeErrRsp(400, "已有产品使用该计量仓库调度，无法删除！");
        } else {
            if (warehouseTransferService.removeById(id)) {
                return new RetResponse().makeOKRsp(200, "SUCCESS");
            } else {
                return new RetResponse().makeErrRsp(400, "FAIL");
            }
        }
    }
}
