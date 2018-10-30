package com.cattsoft.emos.quality.delimiting.service.impl;

import com.cattsoft.emos.quality.delimiting.dao.QualityDelimitingDao;
import com.cattsoft.emos.quality.delimiting.service.QualityDelimitingLmnlService;
import com.cattsoft.emos.quality.delimiting.service.QualityDelimitingWlwService;
import com.cattsoft.emos.quality.delimiting.util.AlgorithmUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QualityDelimitingLmnlServiceImpl implements QualityDelimitingLmnlService {

    @Autowired
    QualityDelimitingDao qualityDelimitingDao;
    @Autowired
    AlgorithmUtil algorithmUtil;
    @Autowired
    QualityDelimitingWlwService qualityDelimitingWlwService;
    @Autowired
    QualityDelimitingLmnlService qualityDelimitingLmnlService;

    @Override
    public String tmnl() throws Exception {
        return null;
    }




    //汇聚处理 //jumpFlag  跳转不同维度标志 1，物联网维度 2，终端维度
    //flag  判断  查找的规则指标
    //drillFlag  钻取维度 标志
    //poorTable  用来输出的质差表名
    //向下钻取 的 限制字段 例：相应地市的基站  相应基站的小区
    //table 相应的 查看的表
    //exportTable    输出的表

    public String openSwitch(int flag, String table,String AlgorithmColumn,int jumpFlag ,String poorTable ,int drillFlag,Map<String,Object> Data) throws Exception {
        Map<String, Object> resMap = qualityDelimitingDao.selectRule(flag);
        List<Map<String, Object>> resLists = new ArrayList<Map<String, Object>>();
        if (resMap != null) {
            Integer Threshold = (Integer) resMap.get("Threshold");
            switch (Threshold) {
                case 1:
                    //大于
                    String operator = ">";
                    List<Map<String, Object>> lists = qualityDelimitingDao.selectTb(resMap, operator, table,Data);
                    resLists = algorithmUtil.Algorithm(lists, AlgorithmColumn);
                    if (resLists != null) {

                        if(drillFlag == 3){
                            //输出终端维度质差
                            //输出质差列表表名
                            String TABLE_NAME = "TB_KR_TMNL_HTTP_MANU_FAULT_DY_POOR";
                            //输出质差列表
                            int resPoor=qualityDelimitingDao.InsertNe(resLists,TABLE_NAME);
                        }

                    } else {

                        if (jumpFlag == 3) {

                            System.out.println();


                        }
                    }
                    break;
                case 2:
                    //小于
                    String operator_xiao = "<";
                    List<Map<String, Object>> lists_xiao = qualityDelimitingDao.selectTb(resMap, operator_xiao, table,Data);
                    resLists = algorithmUtil.Algorithm(lists_xiao, AlgorithmColumn);
                    if (resLists != null) {


                        if(drillFlag == 3){
                            //输出终端维度质差
                            //输出质差列表表名
                            String TABLE_NAME = "TB_KR_TMNL_HTTP_MANU_FAULT_DY_POOR";
                            //输出质差列表
                            int resPoor=qualityDelimitingDao.InsertNe(resLists,TABLE_NAME);

                        }

                    } else {

                        if (jumpFlag == 3) {

                            System.out.println("3");
                            //输出到质差表
                        }
                    }
                    break;
                case 3:
                    //等于
                    String operator_deng = "=";
                    List<Map<String, Object>> lists_deng = qualityDelimitingDao.selectTb(resMap, operator_deng, table,Data);
                    resLists = algorithmUtil.Algorithm(lists_deng, AlgorithmColumn);
                    if (resLists != null) {

                        if(drillFlag == 3){
                            //输出终端维度质差
                            //输出质差列表表名
                            String TABLE_NAME = "TB_KR_TMNL_HTTP_MANU_FAULT_DY_POOR";
                            //输出质差列表
                            int resPoor=qualityDelimitingDao.InsertNe(resLists,TABLE_NAME);

                        }

                    } else {

                        if (jumpFlag == 3) {

                            System.out.println("33");
                            //输出到质差表
                        }
                    }
                    break;
                case 4:
                    //大于等于
                    String operator_dadeng = ">=";
                    List<Map<String, Object>> lists_dadeng = qualityDelimitingDao.selectTb(resMap, operator_dadeng, table,Data);
                    resLists = algorithmUtil.Algorithm(lists_dadeng, AlgorithmColumn);
                    if (resLists != null) {

                        if(drillFlag == 3){
                            //输出终端维度质差
                            //输出质差列表表名
                            String TABLE_NAME = "TB_KR_TMNL_HTTP_MANU_FAULT_DY_POOR";
                            //输出质差列表
                            int resPoor=qualityDelimitingDao.InsertNe(resLists,TABLE_NAME);

                        }

                    } else {

                        if (jumpFlag == 3) {

                            System.out.println("333");
                            //输出到质差表
                        }
                    }
                    break;
                case 5:
                    //小于等于
                    String operator_xiaodeng = "<=";
                    List<Map<String, Object>> lists_xiaodeng = qualityDelimitingDao.selectTb(resMap, operator_xiaodeng, table,Data);
                    resLists = algorithmUtil.Algorithm(lists_xiaodeng, AlgorithmColumn);
                    if (resLists != null) {

                        if(drillFlag == 3){
                            //输出终端维度质差
                            //输出质差列表表名
                            String TABLE_NAME = "TB_KR_TMNL_HTTP_MANU_FAULT_DY_POOR";
                            //输出质差列表
                            int resPoor=qualityDelimitingDao.InsertNe(resLists,TABLE_NAME);

                        }

                    } else {


                        if (jumpFlag == 3) {

                            System.out.println("3333");
                            //输出到质差表
                        }
                    }
                    break;
                case 6:
                    //不等于
                    String operator_budeng = "<>";
                    List<Map<String, Object>> lists_budeng = qualityDelimitingDao.selectTb(resMap, operator_budeng, table,Data);
                    resLists = algorithmUtil.Algorithm(lists_budeng, AlgorithmColumn);
                    if (resLists != null) {

                        if(drillFlag == 3){
                            //输出终端维度质差
                            //输出质差列表表名
                            String TABLE_NAME = "TB_KR_NE_HTTP_ECI_FAULT_DY_POOR";
                            //输出质差列表
                            int resPoor=qualityDelimitingDao.InsertNe(resLists,TABLE_NAME);

                        }

                    }
                    else {

                        if (jumpFlag == 3) {

                            System.out.println("33333");
                            //输出到质差表
                        }
                    }
                    break;

            }
        }
        String res="over";
        return res;
    }
}
