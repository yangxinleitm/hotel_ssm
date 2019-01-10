package cn.hotel.entity;

import java.io.Serializable;
import java.math.BigDecimal;


public class HotelMaterialDto implements Serializable {
    private Long materialsId;
    private Long parentId;
    private String materialTypeNnameEn;
    private String materialTypeName;
    private String materialTypeCode;
    private String materialTypeValue;
    private BigDecimal materialsPrice;
    private String isDelete;
    private Long createTime;
    private Long modifyTime;

    public HotelMaterialDto() {
    }

    public Long getMaterialsId() {
        return materialsId;
    }

    public void setMaterialsId(Long materialsId) {
        this.materialsId = materialsId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMaterialTypeNnameEn() {
        return materialTypeNnameEn;
    }

    public void setMaterialTypeNnameEn(String materialTypeNnameEn) {
        this.materialTypeNnameEn = materialTypeNnameEn;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    public String getMaterialTypeCode() {
        return materialTypeCode;
    }

    public void setMaterialTypeCode(String materialTypeCode) {
        this.materialTypeCode = materialTypeCode;
    }

    public String getMaterialTypeValue() {
        return materialTypeValue;
    }

    public void setMaterialTypeValue(String materialTypeValue) {
        this.materialTypeValue = materialTypeValue;
    }

    public BigDecimal getMaterialsPrice() {
        return materialsPrice;
    }

    public void setMaterialsPrice(BigDecimal materialsPrice) {
        this.materialsPrice = materialsPrice;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "HotelMaterialDto{" +
                "materialsId=" + materialsId +
                ", parentId=" + parentId +
                ", materialTypeNnameEn='" + materialTypeNnameEn + '\'' +
                ", materialTypeName='" + materialTypeName + '\'' +
                ", materialTypeCode='" + materialTypeCode + '\'' +
                ", materialTypeValue='" + materialTypeValue + '\'' +
                ", materialsPrice=" + materialsPrice +
                ", isDelete='" + isDelete + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
