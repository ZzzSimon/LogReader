package com.ddtc.LogReader;

/**
 * Created by Administrator on 2017/9/28.
 */
public class MatchedRequest {
    private String ip = "0.0.0.0";
    private String date = "";
    private String method = "";
    private String urlFront = "";
    private String urlParameters = "";
    private String version = "";
    private int resultNumber = 200;
    private int spend = 0;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrlFront() {
        return urlFront;
    }

    public void setUrlFront(String urlFront) {
        this.urlFront = urlFront;
    }

    public String getUrlParameters() {
        return urlParameters;
    }

    public void setUrlParameters(String urlParameters) {
        this.urlParameters = urlParameters;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getResultNumber() {
        return resultNumber;
    }

    public void setResultNumber(int resultNumber) {
        this.resultNumber = resultNumber;
    }

    public int getSpend() {
        return spend;
    }

    public void setSpend(int spend) {
        this.spend = spend;
    }
}
