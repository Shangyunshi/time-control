package com.shangyunshi.timecontrol.model;

import java.io.Serializable;
import java.util.List;

public class Label implements Serializable {
    public String id;
    public String name;
    public List<AppInfo> appInfos;
}
