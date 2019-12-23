package com.example.platform.pojo;

public class BaseInfo {
    private Integer id;

    private String baseCode;

    private String baseName;

    private String baseType;

    public BaseInfo(Integer id, String baseCode, String baseName, String baseType) {
        this.id = id;
        this.baseCode = baseCode;
        this.baseName = baseName;
        this.baseType = baseType;
    }

    public BaseInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode == null ? null : baseCode.trim();
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName == null ? null : baseName.trim();
    }

    public String getBaseType() {
        return baseType;
    }

    public void setBaseType(String baseType) {
        this.baseType = baseType == null ? null : baseType.trim();
    }
}