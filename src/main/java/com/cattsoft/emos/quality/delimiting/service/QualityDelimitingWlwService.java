package com.cattsoft.emos.quality.delimiting.service;

import java.util.List;
import java.util.Map;

public interface QualityDelimitingWlwService {

    public String wlwApn() throws Exception;
    public String wlwSgw(List<Map<String, Object>> list) throws Exception;
    public String wlwIp(List<Map<String, Object>> list) throws Exception;
}
