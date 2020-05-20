package com.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "ItripTokenVo", description = "用户认证凭据信息")
public class TokenVo implements Serializable {
    /**
     * 用户认证凭据
     */
    @ApiModelProperty("用户认证凭据")
    private String token;
    /**
     * 过期时间
     */
    @ApiModelProperty("过期时间")
    private long expTime;

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpTime(long expTime) {
        this.expTime = expTime;
    }

    public void setGenTime(long genTime) {
        this.genTime = genTime;
    }

    public String getToken() {
        return token;
    }

    public long getExpTime() {
        return expTime;
    }

    public long getGenTime() {
        return genTime;
    }

    /**
     * 生成时间
     */
    @ApiModelProperty("生成时间")
    private long genTime;


    public TokenVo() {
        super();
    }

    public TokenVo(String token, long expTime, long genTime) {
        super();
        this.token = token;
        this.expTime = expTime;
        this.genTime = genTime;
    }

}
