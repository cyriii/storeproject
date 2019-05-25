package com.cyriii.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cyriii.entity.StoreInfo;
import com.cyriii.entity.StoreInfoVO;
import org.apache.catalina.Store;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface StoreInfoDao extends BaseMapper<StoreInfo> {

    @Select({"<script> ",
            "SELECT a.id,a.store_num,b.name AS good_name,b.unit as good_unit",
            "FROM store_info a LEFT JOIN good_info b ON a.good_id = b.id ",
            "<where>",
            "b.user_id = #{entity.userId, jdbcType=VARCHAR} ",
            "<if test=\"entity != null and entity.name != null and entity.name != ''\">",
            "and (b.name like concat(\"%\", #{entity.name,jdbcType=VARCHAR},\"%\")",
            "</if>",
            "</where>",
            "order by b.name",
            "</script>"
    })
    IPage<StoreInfoVO> page(Page page, @Param("entity") Map<String, Object> params);
}
