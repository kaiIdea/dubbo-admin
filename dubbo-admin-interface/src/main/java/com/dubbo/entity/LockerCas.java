package com.dubbo.entity;


import java.io.Serializable;

public class LockerCas implements Serializable {

    private Integer id;
    private String casNo;
    private String smiles;
    private String cid;
    private String cnName;
    private String properties;

    public LockerCas() {
    }

    public LockerCas(String cnName) {
        this.cnName = cnName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCasNo() {
        return casNo;
    }

    public void setCasNo(String casNo) {
        this.casNo = casNo;
    }

    public String getSmiles() {
        return smiles;
    }

    public void setSmiles(String smiles) {
        this.smiles = smiles;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"casNo\":\"")
                .append(casNo).append('\"');
        sb.append(",\"smiles\":\"")
                .append(smiles).append('\"');
        sb.append(",\"cid\":\"")
                .append(cid).append('\"');
        sb.append(",\"cnName\":\"")
                .append(cnName).append('\"');
        sb.append(",\"properties\":\"")
                .append(properties).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
