package com.mal.common;

import com.google.common.collect.Sets;

import java.util.Set;

//放置常量的类

public class Const {

    //判断是管理员登录还是普通用户登录
    public static final String CURRENT_USER = "currentUser";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    //封装排序
    public interface ProductListOrderBy{
        //price_desc:用下划线作为分割，前面表示orderby哪个字段，后面表示排序规则：升序还是降序
        //set时间复杂度是O(1)，list的时间复杂度是O(n)
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc","price_asc");
    }

    /**
     * 普通用户和管理员是一个组，也可用枚举，但是此处枚举显得过于繁重，
     * 通过内部的接口类来把常量进行分组
     */
    public interface Role{
        int ROLE_CUSTOMER = 0;  //普通用户
        int ROLE_ADMIN = 1;     //管理员
    }


    public interface Cart{
        int CHECKED = 1;        //购物车商品是选中状态
        int UN_CHECKED = 0;     //购物车商品是未选中状态

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";

    }



    //判断商品的销售状态
    public enum ProductStatusEnum{
        ON_SALE("在线", 1);

        private String value;
        private int code;

        ProductStatusEnum(String value, int code) {
            this.value = value;
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    //订单状态
    public enum OrderStatusEnum{
        CANCELED("取消", 0),
        NO_PAY("未支付", 10),
        PAID("已付款", 20),
        SHIPPED("已发货", 40),
        ORDER_SUCCESS("订单完成", 50),
        ORDER_CLOSE("订单关闭", 60);

        OrderStatusEnum(String value, int code) {
            this.value = value;
            this.code = code;
        }

        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static OrderStatusEnum codeOf(int code){
            for(OrderStatusEnum orderStatusEnum : values()){
                if(orderStatusEnum.getCode() == code){
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }

    }

    public interface AlipayCallback{
        String TRADE_STATUS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
        String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";

        String RESPONSE_SUCCESS = "success";
        String RESPONSE_FAILED = "failed";
    }

    public enum PayPlatformEnum{
        ALIPAY(1,"支付宝");

        PayPlatformEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }


    public enum PaymentTypeEnum{
        ONLINE_PAY(1,"在线支付");

        PaymentTypeEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static PaymentTypeEnum codeOf(int code){
            for(PaymentTypeEnum paymentTypeEnum : values()){
                if(paymentTypeEnum.getCode() == code){
                    return paymentTypeEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }
    }

}
