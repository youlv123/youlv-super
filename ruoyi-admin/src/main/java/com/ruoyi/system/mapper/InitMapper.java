package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.ItemImageDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 初始化
 *
 * @author ruoyi
 * @date 2024-07-30
 */
@Repository
public interface InitMapper {

    List<ItemImageDTO> selectItem(@Param("maxId") Long maxId);

}
