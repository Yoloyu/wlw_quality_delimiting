package com.cattsoft.emos.quality.delimiting.dao;

import java.util.List;
import java.util.Map;

public interface QualityDelimitingDao {
    public List<Map<String, Object>> selectTb(Map map,String operator,String table,Map<String, Object>Data,List riqiList);
    public List<Map<String, Object>> selectLog(String AlgorithmColumn);
    public int  InsertNe(List<Map<String, Object>> list,String TABLE_NAME);
    public int InsertLog(Long statis_time, String AlgorithmColumn);
    public Map<String, Object> selectRule(int flag);

}
