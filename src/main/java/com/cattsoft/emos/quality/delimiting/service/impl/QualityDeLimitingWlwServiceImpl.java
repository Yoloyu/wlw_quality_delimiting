package com.cattsoft.emos.quality.delimiting.service.impl;

import com.cattsoft.emos.quality.delimiting.dao.QualityDelimitingDao;
import com.cattsoft.emos.quality.delimiting.service.QualityDelimitingLmnlService;
import com.cattsoft.emos.quality.delimiting.service.QualityDelimitingWlwService;
import com.cattsoft.emos.quality.delimiting.util.AlgorithmUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class QualityDeLimitingWlwServiceImpl implements QualityDelimitingWlwService {

    @Autowired
    QualityDelimitingDao qualityDelimitingDao;
    @Autowired
    AlgorithmUtil algorithmUtil;

    @Autowired
    QualityDelimitingLmnlService qualityDelimitingLmnlService;

    //wlw 维度 第一次汇聚 apn
    @Override
    public String wlwApn() throws Exception {
        int flag = 39;
        //TCP接入时长
        String table = "TB_KR_WLW_HTTP_APN_FAULT_DY";
        String AlgorithmColumn ="tcp_access_time";
        int jumpFlag = 1;
        String poorTable="";
        int drillFlag = 1 ;
        Map<String,Object>Data= null;
        openSwitch(flag,table,AlgorithmColumn,jumpFlag,poorTable,drillFlag,Data);
        return "over";
    }

    //wlw 维度 第二次汇聚 sgw
    @Override
    public String wlwSgw(List<Map<String, Object>> list) throws Exception {
        int flag = 39;
        String table = "TB_KR_WLW_HTTP_GGSN_FAULT_DY";
        String AlgorithmColumn ="tcp_access_time";
        int jumpFlag = 1;
        String poorTable="";
        int drillFlag = 2 ;
        //输出质差列表表名
        String TABLE_NAME = "TB_KR_WLW_HTTP_GGSN_FAULT_DY_POOR";
        //输出质差列表
        int res=qualityDelimitingDao.InsertNe(list,TABLE_NAME);

        for (Map<String,Object>Data:list) {
            openSwitch(flag,table,AlgorithmColumn,jumpFlag,poorTable,drillFlag,Data);
        }


        String a = "1";
        return a;
    }

    //wlw 维度 第三次汇聚 ip
    @Override
    public String wlwIp(List<Map<String, Object>> list) throws Exception {
        int flag = 39;
        String table = "TB_KR_WLW_HTTP_IP_FAULT_DY";
        String AlgorithmColumn ="tcp_access_time";
        int jumpFlag = 1;
        String poorTable="";
        int drillFlag = 3 ;
        //输出质差列表表名
        String TABLE_NAME = "TB_KR_WLW_HTTP_IP_FAULT_DY_POOR";
        //输出质差列表
        int res=qualityDelimitingDao.InsertNe(list,TABLE_NAME);

        for (Map<String,Object>Data:list) {
            openSwitch(flag,table,AlgorithmColumn,jumpFlag,poorTable,drillFlag,Data);
        }
        String a = "1";
        return a;
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
                        //向下钻取
                        if (drillFlag == 1) {
                            wlwSgw(resLists);
                        }

                        if(drillFlag == 3){
                            //输出小区维度质差
                            //输出质差列表表名
                            String TABLE_NAME = "TB_KR_WLW_HTTP_IP_FAULT_DY";
                            //输出质差列表
                            int resPoor=qualityDelimitingDao.InsertNe(resLists,TABLE_NAME);
                        }

                    } else {
                        // 跳转到物联网维度
                        if (jumpFlag == 1) {
                            qualityDelimitingLmnlService.tmnl();
                        }
                        //跳转到 终端维度
                        if (jumpFlag == 2) {
                            qualityDelimitingLmnlService.tmnl();
                        }
                        //向下钻取IP 层
                        if (drillFlag == 2) {
                            wlwIp(resLists);
                        }
                        if (jumpFlag == 3) {

                            System.out.println();
                            //输出到质差表

                        }
                    }
                    break;
                case 2:
                    //小于
                    String operator_xiao = "<";
                    List<Map<String, Object>> lists_xiao = qualityDelimitingDao.selectTb(resMap, operator_xiao, table,Data);
                    resLists = algorithmUtil.Algorithm(lists_xiao, AlgorithmColumn);
                    if (resLists != null) {
                        //向下钻取
                        if (drillFlag == 1) {
                            wlwSgw(resLists);
                        }

                        if(drillFlag == 3){
                            //输出小区维度质差
                            //输出质差列表表名
                            String TABLE_NAME = "TB_KR_WLW_HTTP_IP_FAULT_DY";
                            //输出质差列表
                            int resPoor=qualityDelimitingDao.InsertNe(resLists,TABLE_NAME);

                        }

                    } else {
                        // 跳转到物联网维度!
                        if (jumpFlag == 1) {
                            qualityDelimitingLmnlService.tmnl();
                        }
                        //跳转到 终端维度
                        if (jumpFlag == 2) {
                            qualityDelimitingLmnlService.tmnl();
                        }
                        //向下钻取IP 层
                        if (drillFlag == 2) {
                            wlwIp(resLists);
                        }
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
                        //向下钻取
                        if (drillFlag == 1) {
                            wlwSgw(resLists);
                        }

                        if(drillFlag == 3){
                            //输出小区维度质差
                            //输出质差列表表名
                            String TABLE_NAME = "TB_KR_WLW_HTTP_IP_FAULT_DY";
                            //输出质差列表
                            int resPoor=qualityDelimitingDao.InsertNe(resLists,TABLE_NAME);

                        }

                    } else {
                        // 跳转到物联网维度!
                        if (jumpFlag == 1) {
                            qualityDelimitingLmnlService.tmnl();
                        }
                        //跳转到 终端维度
                        if (jumpFlag == 2) {
                            qualityDelimitingLmnlService.tmnl();
                        }

                        //向下钻取IP 层
                        if (drillFlag == 2) {
                            wlwIp(resLists);
                        }
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
                        //向下钻取
                        if (drillFlag == 1) {
                            wlwSgw(resLists);
                        }

                        if(drillFlag == 3){
                            //输出小区维度质差
                            //输出质差列表表名
                            String TABLE_NAME = "TB_KR_WLW_HTTP_IP_FAULT_DY";
                            //输出质差列表
                            int resPoor=qualityDelimitingDao.InsertNe(resLists,TABLE_NAME);

                        }

                    } else {
                        // 跳转到物联网维度!
                        if (jumpFlag == 1) {
                            qualityDelimitingLmnlService.tmnl();
                        }
                        //跳转到 终端维度
                        if (jumpFlag == 2) {
                            qualityDelimitingLmnlService.tmnl();
                        }

                        //向下钻取IP 层
                        if (drillFlag == 2) {
                            wlwIp(resLists);
                        }
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
                        //向下钻取
                        if (drillFlag == 1) {
                            wlwSgw(resLists);
                        }

                        if(drillFlag == 3){
                            //输出小区维度质差
                            //输出质差列表表名
                            String TABLE_NAME = "TB_KR_WLW_HTTP_IP_FAULT_DY";
                            //输出质差列表
                            int resPoor=qualityDelimitingDao.InsertNe(resLists,TABLE_NAME);

                        }

                    } else {
                        // 跳转到物联网维度!
                        if (jumpFlag == 1) {
                            qualityDelimitingLmnlService.tmnl();
                        }
                        //跳转到 终端维度
                        if (jumpFlag == 2) {
                            qualityDelimitingLmnlService.tmnl();
                        }
                        //向下钻取IP 层
                        if (drillFlag == 2) {
                            wlwIp(resLists);
                        }

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
                        //向下钻取
                        if (drillFlag == 1) {
                            wlwSgw(resLists);
                        }

                        if(drillFlag == 3){
                            //输出小区维度质差
                            //输出质差列表表名
                            String TABLE_NAME = "TB_KR_WLW_HTTP_IP_FAULT_DY";
                            //输出质差列表
                            int resPoor=qualityDelimitingDao.InsertNe(resLists,TABLE_NAME);

                        }

                    }
                    else {
                        // 跳转到物联网维度!
                        if (jumpFlag == 1) {
                            qualityDelimitingLmnlService.tmnl();
                        }
                        //跳转到 终端维度
                        if (jumpFlag == 2) {
                            qualityDelimitingLmnlService.tmnl();
                        }

                        //向下钻取IP 层
                        if (drillFlag == 2) {
                            wlwIp(resLists);
                        }

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
