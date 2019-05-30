package com.mal.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/*

    泛型构造的服务端响应对象
    返回给前端的数据都会统一规范起来，用一个泛型来作为响应对象

 */


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
/*作用：
 * 对于失败的情况，不返回data，只返回status和msg时，默认为有key的空节点，即有key，但value是null，
 * 这种是不需要返回给前端的，就在类上使用这个注解，保证序列化json的时候，如果是null的对象，key也会消失。
 * */

public class ServerResponse<T> implements Serializable {

    private int status;     //接口调用状态码
    private String msg;     //接口需要响应的信息
    private T data;         //泛型数据对象

    //私有的构造器

    //只有状态码，对应业务办理类接口返回类型
    private ServerResponse(int status){
        this.status = status;
    }

    //状态码和泛型数据，对应业务查询类型接口返回
    private ServerResponse(int status,T data){
        this.status = status;
        this.data = data;
    }

    //状态码、响应信息、泛型数据，对应业务查询类型接口返回；
    private ServerResponse(int status,String msg ,T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    //状态码和响应信息，对应业务办理类接口返回类型
    private ServerResponse(int status,String msg){
        this.status = status;
        this.msg = msg;
    }

    //响应是否正确的判断,通过注解在序列化时，isSuccess方法就不会在序列化里
    @JsonIgnore
    //使之不在json序列化结果当中
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();    //是0返回true，否则false
    }


    //get方法
    public int getStatus(){
        return status;
    }

    public T getData(){
        return data;
    }

    public String getMsg(){
        return msg;
    }

    //声明这个类，创建这个类，通过相应成功后，通过成功标志来构件这个类，最终返回一个status
    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode());
    }

    //通过文本显示成功，创建这个类，作用是供前端提示使用
    public static <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),msg);
    }

    //相应成功然后给前台相应数据
    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),data);
    }

    //相应成功然后给前台相应数据和文本消息
    public static <T> ServerResponse<T> createBySuccess(String msg,T data){
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    //响应失败时，直接把错误返回
    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }
    //直接返回错误信息
    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<>(ResponseCode.ERROR.getCode(),errorMessage);
    }

    //服务端响应，需要登录/参数错误
    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ServerResponse<>(errorCode,errorMessage);
    }

}
