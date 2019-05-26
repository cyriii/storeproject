package com.cyriii.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cyriii.entity.InStoreInfo;
import com.cyriii.entity.InStoreInfoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface InStoreInfoDao extends BaseMapper<InStoreInfo> {

    @Select({"<script> ",
            "SELECT a.id,a.supply_num,a.supply_univalence,a.supply_date,b.name AS supplier_name,b.link_man,b.tel_number,c.name as good_name,c.unit ",
            "FROM in_store_info a LEFT JOIN supplier_info b ON a.supplier_id = b.id LEFT JOIN good_info c ON a.good_id = c.id ",
            "<where>",
                "a.user_id = #{entity.userId, jdbcType=VARCHAR} ",
                "<if test=\"entity != null and entity.name != null and entity.name != ''\">",
                "and (b.name like concat(\"%\", #{entity.name,jdbcType=VARCHAR},\"%\") or c.name like concat(\"%\", #{entity.name,jdbcType=VARCHAR},\"%\")) ",
                "</if>",
            "</where>",
            "order by a.create_date desc",
            "</script>"
    })
    IPage<InStoreInfoVO> page(Page<InStoreInfoVO> page, @Param("entity") Map<String, Object> params);

}
