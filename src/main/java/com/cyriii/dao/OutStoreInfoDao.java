package com.cyriii.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cyriii.entity.OutStoreInfo;
import com.cyriii.entity.OutStoreInfoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface OutStoreInfoDao extends BaseMapper<OutStoreInfo> {

    @Select({"<script> ",
            "SELECT a.id,a.demand_num,a.demand_univalence,b.name AS customer_name,b.link_man,b.tel_number,c.name as good_name,c.unit ",
            "FROM out_store_info a LEFT JOIN customer_info b ON a.customer_id = b.id LEFT JOIN good_info c ON a.good_id = c.id ",
            "<where>",
            "a.user_id = #{entity.userId, jdbcType=VARCHAR} ",
            "<if test=\"entity != null and entity.name != null and entity.name != ''\">",
            "and (b.name like concat(\"%\", #{entity.name,jdbcType=VARCHAR},\"%\") or c.name like concat(\"%\", #{entity.name,jdbcType=VARCHAR},\"%\")) ",
            "</if>",
            "</where>",
            "order by a.create_date desc",
            "</script>"
    })
    IPage<OutStoreInfoVO> page(Page<OutStoreInfoVO> page, @Param("entity") Map<String, Object> params);
}
