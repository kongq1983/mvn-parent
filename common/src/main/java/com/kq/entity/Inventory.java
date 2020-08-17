package com.kq.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 存货档案
 * TODO
 * @author kongqi
 * @date 2017-10-13 13:49
 */

@Data
public class Inventory {
	
    private String id;

    /**
     * 存货编码
     */
    private String code;

    /**
     * 存货名称
     */
    private String name;

    /**
     * 大类
     */
    private String bigCategory;
    private String bigCategoryName;

    /**
     * 小类
     */
    private String smallCategory;
    private String smallCategoryName;

    /**
     * 规格编号
     */
    private String specification;

    /**
     * 主计量单位
     */
    private String primaryUnit;

    /**
     * 采购价格
     */
    private BigDecimal purchasePrice;

    /**
     * 销售价格
     */
    private BigDecimal salePrice;


    /**
     * 保质期
     */
    private Integer period;

    /**
     * 图片
     */
    private String photo;

    /**
     * 存货类型 0:原材料  1:菜品  2:套餐
     */
    private Integer type;

    /**
     * 机构ID
     */
    private String orgId;
    private String orgName;

    /**
     * 租户ID
     */
    private String tenantId; /**
     * 租户ID
     */
    private String tenantName;

    /**
     * 助记码
     */
    private String mnemonicCode;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;


    private String createUser;
    private String createUserName;


    private Date createTime;


    private String lastModifyUser;
    private String lastModifyUserName;


    private Date lastModifyTime;





}