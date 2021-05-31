package cn.cupbread.mims.DAO;

import cn.cupbread.mims.Entity.Inventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryDAO extends BaseMapper<Inventory> {
}