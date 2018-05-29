package com.ale.common.util.eolinker;

import lombok.Data;

import java.util.List;


@Data
public class MockInfo {
    /**
     * rule : [{"paramKey":"","paramType":"0","$index":0}]
     * result : {}
     * mockConfig : {"rule":"","type":"object"}
     */

    private String result;
    private MockConfig mockConfig;
    private List<Rule> rule;




}

