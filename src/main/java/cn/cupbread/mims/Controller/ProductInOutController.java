package cn.cupbread.mims.Controller;

import cn.cupbread.mims.Component.RetResponse;
import cn.cupbread.mims.Entity.*;
import cn.cupbread.mims.Service.*;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/1
 * @description :
 */
@RequestMapping("/product")
@Api(tags = {"出入库控制类"})
@RestController
public class ProductInOutController {

    @Autowired
    private ProductInRecordService productInRecordService;
    @Autowired
    private ProductOutRecordService productOutRecordService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private ExpressService expressService;

    @ApiOperation("产品入库")
    @PostMapping("/in")
    public RetResponse addProductInRecord(ProductInRecord record) {
        if (productInRecordService.productIn(record, 1L)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("获取入库记录-通过id")
    @PostMapping("/in/id")
    public RetResponse getProductInRecord(@ApiParam(value = "id", example = "1", required = true) Long id) {
        ProductInRecord record = productInRecordService.getById(id);
        if (record == null) return new RetResponse().makeOKRsp(200, "NULL", null);
        User user = userService.getById(record.getUId());
        Product product = productService.getById(record.getPId());
        Supplier supplier = supplierService.getById(record.getSId());
        Warehouse warehouse = warehouseService.getById(record.getWId());
        JSONObject res = JSONUtil.createObj();
        res.putOpt("record", record);
        res.putOpt("user", user);
        res.putOpt("product", product);
        res.putOpt("supplier", supplier);
        res.putOpt("warehouse", warehouse);
        return new RetResponse().makeOKRsp(200, "SUCCESS", res);
    }

    @ApiOperation("获取入库记录-通过id")
    @PostMapping("/out/id")
    public RetResponse getProductOutRecord(@ApiParam(value = "id", example = "1", required = true) Long id) {
        ProductOutRecord record = productOutRecordService.getById(id);
        if (record == null) return new RetResponse().makeOKRsp(200, "NULL", null);
        User user = userService.getById(record.getUId());
        Product product = productService.getById(record.getPId());
        Warehouse warehouse = warehouseService.getById(record.getWId());
        Express express = expressService.getById(record.getExpressId());
        JSONObject res = JSONUtil.createObj();
        res.putOpt("record", record);
        res.putOpt("user", user);
        res.putOpt("product", product);
        res.putOpt("warehouse", warehouse);
        res.putOpt("express", express);
        return new RetResponse().makeOKRsp(200, "SUCCESS", res);
    }


    @ApiOperation("入库查询")
    @GetMapping("/in/record")
    public RetResponse getProductInRecord(@ApiParam(value = "当前页", example = "1", required = true) Long current,
                                          @ApiParam(value = "每页大小", example = "10", required = true) Long size,
                                          @ApiParam(value = "开始时间", example = "2021-6-4", required = true) Date startTime,
                                          @ApiParam(value = "结束时间", example = "2021-6-4", required = true) Date endTime,
                                          @ApiParam(value = "序列号") String serialNumber,
                                          @ApiParam(value = "仓库id", example = "1") Long wId,
                                          @ApiParam(value = "用户id", example = "1") Long uId,
                                          @ApiParam(value = "产品id", example = "1") Long pId,
                                          @ApiParam(value = "供应商id", example = "1") Long sId) {
        //分页
        Page<ProductInRecord> page = new Page<>(current, size);
        //将要查询的字段放入Map
        Map<String, Object> map = new HashMap<>();
        map.put("w_id", wId);
        map.put("p_id", pId);
        map.put("s_id", sId);
        map.put("u_id", uId);
        map.put("serial_number", serialNumber);
        QueryWrapper<ProductInRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(map, false).between("create_time", startTime, endTime);
        IPage<ProductInRecord> recordIPage = productInRecordService.page(page, queryWrapper);
        List<ProductInRecord> inRecordList = recordIPage.getRecords();
        if (inRecordList.size() == 0) return new RetResponse().makeOKRsp(200, null);
        //将记录内的id提取出来
        Set<Long> userId = new HashSet<>();
        Set<Long> productId = new HashSet<>();
        Set<Long> supplierId = new HashSet<>();
        Set<Long> warehouseId = new HashSet<>();
        for (ProductInRecord record : inRecordList) {
            userId.add(record.getUId());
            productId.add(record.getPId());
            supplierId.add(record.getSId());
            warehouseId.add(record.getWId());
        }
        List<User> userList = userService.listByIds(userId);
        List<Product> productList = productService.listByIds(productId);
        List<Warehouse> warehouseList = warehouseService.listByIds(warehouseId);
        List<Supplier> supplierList = supplierService.listByIds(supplierId);

        JSONObject res = JSONUtil.createObj();
        res.putOpt("recordList", inRecordList);
        res.putOpt("userList", userList);
        res.putOpt("productList", productList);
        res.putOpt("warehouseList", warehouseList);
        res.putOpt("supplierList", supplierList);
        res.putOpt("total", recordIPage.getTotal());

        return new RetResponse().makeOKRsp(200, res);
    }

    @ApiOperation("出库查询")
    @GetMapping("/out/record")
    public RetResponse getProductOutRecord(@ApiParam(value = "当前页", example = "1", required = true) Long current,
                                           @ApiParam(value = "每页大小", example = "10", required = true) Long size,
                                           @ApiParam(value = "开始时间", example = "2021-6-4", required = true) Date startTime,
                                           @ApiParam(value = "结束时间", example = "2021-6-4", required = true) Date endTime,
                                           @ApiParam(value = "序列号") String serialNumber,
                                           @ApiParam(value = "仓库id", example = "1") Long wId,
                                           @ApiParam(value = "用户id", example = "1") Long uId,
                                           @ApiParam(value = "产品id", example = "1") Long pId) {
        Page<ProductOutRecord> page = new Page<>(current, size);
        Map<String, Object> map = new HashMap<>();
        map.put("w_id", wId);
        map.put("p_id", pId);
        map.put("u_id", uId);
        map.put("serial_number", serialNumber);
        QueryWrapper<ProductOutRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(map, false).between("create_time", startTime, endTime);
        IPage<ProductOutRecord> recordIPage = productOutRecordService.page(page, queryWrapper);
        List<ProductOutRecord> outRecordList = recordIPage.getRecords();
        if (outRecordList.size() == 0) return new RetResponse().makeOKRsp(200, null);
        //将记录内的id提取出来
        Set<Long> userId = new HashSet<>();
        Set<Long> productId = new HashSet<>();
        Set<Long> warehouseId = new HashSet<>();
        Set<Long> expressId = new HashSet<>();
        for (ProductOutRecord record : outRecordList) {
            userId.add(record.getUId());
            productId.add(record.getPId());
            warehouseId.add(record.getWId());
            expressId.add(record.getExpressId());
        }
        List<User> userList = userService.listByIds(userId);
        List<Product> productList = productService.listByIds(productId);
        List<Warehouse> warehouseList = warehouseService.listByIds(userId);
        List<Express> expressList = expressService.listByIds(expressId);

        JSONObject res = JSONUtil.createObj();
        res.putOpt("recordList", outRecordList);
        res.putOpt("userList", userList);
        res.putOpt("productList", productList);
        res.putOpt("warehouseList", warehouseList);
        res.putOpt("expressList", expressList);
        res.putOpt("total", recordIPage.getTotal());


        return new RetResponse().makeOKRsp(200, res);
    }
}
