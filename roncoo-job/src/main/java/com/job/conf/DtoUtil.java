package com.job.conf;

/**
 * 用于返回Dto的工具类
 * Created by XX on 17-5-8.
 */
public class DtoUtil {

    public static String success="true";

    public static String fail="false";

    public static String errorCode="0";
    /***
     * 统一返回成功的DTO
     */
    public static DTO returnSuccess(){
        DTO dto=new DTO();
        dto.setSuccess(success);
        return  dto;
    }
    /***
     * 统一返回成功的DTO 带数据
     */
    public static DTO returnSuccess(String message,Object data){
        DTO dto=new DTO();
        dto.setSuccess(success);
        dto.setMsg(message);
        dto.setErrorCode(errorCode);
        dto.setData(data);
        return  dto;
    }
    /***
     * 统一返回成功的DTO 不带数据
     */
    public static DTO returnSuccess(String message){
        DTO dto=new DTO();
        dto.setSuccess(success);
        dto.setMsg(message);
        dto.setErrorCode(errorCode);
        return  dto;
    }
    /***
     * 统一返回成功的DTO 带数据 没有消息
     */
    public static DTO returnDataSuccess(Object data){
        DTO dto=new DTO();
        dto.setSuccess(success);
        dto.setErrorCode(errorCode);
        dto.setData(data);
        return  dto;
    }

    public static DTO returnFail(String message,String errorCode){
        DTO dto=new DTO();
        dto.setSuccess(fail);
        dto.setMsg(message);
        dto.setErrorCode(errorCode);
        return  dto;
    }
}
