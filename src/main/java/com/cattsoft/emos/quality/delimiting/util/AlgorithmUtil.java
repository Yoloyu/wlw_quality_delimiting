package com.cattsoft.emos.quality.delimiting.util;

import java.util.List;
import java.util.Map;

public class AlgorithmUtil {



    public List<Map<String, Object>> Algorithm(List<Map<String, Object>>lists,String AlgorithmColumn)
    {
        double aver_tcp_access_time=0.0;
        int count_tcp_access_time = 0;
        List<Map<String, Object>> resMap=null;
        for (Map<String,Object> Data : lists) {
            int tcp_access_time=(int)Data.get(AlgorithmColumn);
            //tcp_access_time计算平均值
             count_tcp_access_time=tcp_access_time+count_tcp_access_time;
             aver_tcp_access_time=count_tcp_access_time/lists.size();

        }
        for  (Map<String,Object> Data : lists) {
            //比较 超过平均值上下20%为异常数据 取出
            int tcp_access_time=(int)Data.get(AlgorithmColumn);
            double high_aver_tcp_access_time = 1.2*aver_tcp_access_time;
            double low_aver_tcp_access_time=1.2*aver_tcp_access_time;

            if(tcp_access_time>high_aver_tcp_access_time||tcp_access_time<low_aver_tcp_access_time)
            {
                resMap.add(Data);
            }

        }

        return resMap;
    }

}
