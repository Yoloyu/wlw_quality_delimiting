package com.cattsoft.emos.quality.delimiting.dao.impl;

import com.cattsoft.emos.quality.delimiting.dao.QualityDelimitingDao;
import com.cattsoft.emos.quality.delimiting.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class QualityDelimitingDaoImpl implements QualityDelimitingDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> selectTb(Map map, String operator, String table, Map<String, Object> Data,List riqiList) {
        String sql = sqlString(map, operator, Data);
        List<Map<String, Object>> lists= new ArrayList<Map<String, Object>>();
        HashMap<String,List> paramMap = new HashMap<String,List>();
        paramMap.put("list",riqiList);
        if (riqiList!=null) {
            lists  = jdbcTemplate.queryForList("  select * from    " + table + sql+"statis_time in (:list)" );
        }
        else{
             lists = jdbcTemplate.queryForList("  select * from    " + table + sql);
        }


        System.out.println(lists);
        return lists;

    }

    public List selectLog(String AlgorithmColumn){

        StringBuffer sql = new StringBuffer();
        sql.append("  where 1=1 ");

            sql.append(" and   service = " + AlgorithmColumn);

        List lists = jdbcTemplate.queryForList("  select statis_time from   TB_KR_NE_HTTP_GGSN_FAULT_DY_LOG "+sql);

        return  lists ;
    }


    public Map<String, Object> selectRule(int flag) {
        StringBuffer sql = new StringBuffer();
        sql.append("  where 1=1 ");
        // 根据界面传的标志判断 是哪一条告警规则 并获取相应的阈值与阈值符号
        if (flag != 0) {
            sql.append(" and   Alarm_rule_seq_no = " + flag);
        }
        Map<String, Object> list = jdbcTemplate.queryForMap("select * from TB_ALARM_POLICY_MANAGEMENT " + sql);
        System.out.println(list);
        return list;
    }

    public int InsertLog(Long statis_time, String AlgorithmColumn) {
        StringBuffer sql = new StringBuffer();
        sql.append(" (    "  + statis_time + " , "+AlgorithmColumn + " ) ");

        int res =jdbcTemplate.update("insert into TB_KR_NE_HTTP_GGSN_FAULT_DY_LOG values " +sql);
        return  res;
    }

    public int InsertNe(List<Map<String, Object>> list, String TABLE_NAME) {
        StringBuffer sql = new StringBuffer();
        int res = 0;
        for (Map<String, Object> Data : list) {
            sql.append("INSERT INTO " + TABLE_NAME + " VALUES(");
            if (StringUtil.checkObj(Data.get("statis_time"))) {
                sql.append(Data.get("statis_time") + ",");
            }
            if (StringUtil.checkObj(Data.get("prov_code"))) {
                sql.append(Data.get("prov_code") + ",");
            }
            if (StringUtil.checkObj(Data.get("city_code"))) {
                sql.append(Data.get("city_code") + ",");
            }
            if (StringUtil.checkObj(Data.get("prov_name"))) {
                sql.append(Data.get("prov_name") + ",");
            }
            if (StringUtil.checkObj(Data.get("city_name"))) {
                sql.append(Data.get("city_name") + ",");
            }
            if (StringUtil.checkObj(Data.get("src_u_ip_type"))) {
                sql.append(Data.get("src_u_ip_type") + ",");
            }
            if (StringUtil.checkObj(Data.get("src_u_ip"))) {
                sql.append(Data.get("src_u_ip") + ",");
            }
            if (StringUtil.checkObj(Data.get("enodeb_id"))) {
                sql.append(Data.get("enodeb_id") + ",");
            }
            if (StringUtil.checkObj(Data.get("eci"))) {
                sql.append(Data.get("eci") + ",");
            }
            if (StringUtil.checkObj(Data.get("eci_name"))) {
                sql.append(Data.get("eci_name") + ",");
            }
            if (StringUtil.checkObj(Data.get("online_users_no"))) {
                sql.append(Data.get("online_users_no") + ",");
            }
            if (StringUtil.checkObj(Data.get("tcp_access_time"))) {
                sql.append(Data.get("tcp_access_time") + ",");
            }
            if (StringUtil.checkObj(Data.get("http_access_time"))) {
                sql.append(Data.get("http_access_time") + ",");
            }
            if (StringUtil.checkObj(Data.get("down_speed_rate"))) {
                sql.append(Data.get("down_speed_rate") + ",");
            }
            if (StringUtil.checkObj(Data.get("tcp_retry_pkg_rate"))) {
                sql.append(Data.get("tcp_retry_pkg_rate  ") + ",");
            }
            if (StringUtil.checkObj(Data.get("tcp_unorder_pkg_rate"))) {
                sql.append(Data.get("tcp_unorder_pkg_rate"));
            }
            sql.append(");");
            res = jdbcTemplate.update(sql.toString());
        }
        return res;

    }

    public String sqlString(Map map, String operator, Map<String, Object> Data) {
        StringBuffer sql = new StringBuffer();
        sql.append("  where 1=1 ");
        if (StringUtil.checkObj(Data.get("statis_time"))) {
            sql.append(" and statis_time   " + operator + Data.get("statis_time"));

        }
        if (StringUtil.checkObj(Data.get("prov_code"))) {
            sql.append(" and prov_code   " + operator + Data.get("prov_code"));

        }
        if (StringUtil.checkObj(Data.get("src_u_ip"))) {
            sql.append(" and src_u_ip   " + operator + Data.get("src_u_ip"));

        }
        if (StringUtil.checkObj(Data.get("enodeb_id"))) {
            sql.append(" and enodeb_id   " + operator + Data.get("enodeb_id"));

        }


        if (StringUtil.checkObj(map.get("http_access_time"))) {
            sql.append(" and http_access_time   " + operator + map.get("Threshold"));

        }
        if (StringUtil.checkObj(map.get("down_speed_rate"))) {
            sql.append(" and down_speed_rate   " + operator + map.get("Threshold"));

        }
        if (StringUtil.checkObj(map.get("tcp_retry_pkg_rate"))) {
            sql.append(" and tcp_retry_pkg_rate   " + operator + map.get("Threshold"));

        }
        if (StringUtil.checkObj(map.get("tcp_unorder_pkg_rate"))) {
            sql.append(" and tcp_unorder_pkg_rate   " + operator + map.get("Threshold"));

        }
        if (StringUtil.checkObj(map.get("tcp_unorder_pkg_rate"))) {
            sql.append(" and tcp_unorder_pkg_rate   " + operator + map.get("Threshold"));

        }

        if (StringUtil.checkObj(map.get("tcp_unorder_pkg_rate"))) {
            sql.append(" and tcp_unorder_pkg_rate   " + operator + map.get("Threshold"));

        }
        String sqlString = sql.toString();
        return sqlString;
    }




}
