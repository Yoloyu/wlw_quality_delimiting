package com.cattsoft.emos.quality.delimiting.service;


import java.util.List;
import java.util.Map;

public interface QualityDelimitingService {


    public String neGgsn() throws Exception;
    public String neEnb(List<Map<String, Object>> list) throws Exception;
    public String neEci(List<Map<String, Object>> list) throws Exception;
}
