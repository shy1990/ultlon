package com.sj1688.ultlon.dao.oracle;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface B2BDao {
    public List<Map<String,Serializable>> findSomthing(@Param("name")String name );
    /**
     * //TODO 查询 订单号，签收时间，商品名称 
     * @param skucode
     * @param ddNum
     * @return ORDER_NUM  RECEIVE_TIME GOODS_NAME
     */
	public Map<String, Serializable> findNRGBy(@Param("skucode")String skucode,@Param("ddNum") String ddNum);
}