package cn.hotel.entity;

import java.math.BigDecimal;

public class HotelFoodDto {
    private String foodId;

    private String foodNo;

    private String foodName;

    private String foodCode;

    private String foodClassId;

    private String foodSubjectId;

    private BigDecimal foodPrice;

    private Long customerId;

    private Long applyUserId;

    private Long authUserId;

    private String applyContent;

    private String authContent;

    private String image;

    private String remark;

    private Long creatTime;

    private Long modifyTime;

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodNo() {
        return foodNo;
    }

    public void setFoodNo(String foodNo) {
        this.foodNo = foodNo == null ? null : foodNo.trim();
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName == null ? null : foodName.trim();
    }

    public String getFoodCode() {
        return foodCode;
    }

    public void setFoodCode(String foodCode) {
        this.foodCode = foodCode == null ? null : foodCode.trim();
    }

    public String getFoodClassId() {
        return foodClassId;
    }

    public void setFoodClassId(String foodClassId) {
        this.foodClassId = foodClassId == null ? null : foodClassId.trim();
    }

    public String getFoodSubjectId() {
        return foodSubjectId;
    }

    public void setFoodSubjectId(String foodSubjectId) {
        this.foodSubjectId = foodSubjectId == null ? null : foodSubjectId.trim();
    }

    public BigDecimal getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(BigDecimal foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Long applyUserId) {
        this.applyUserId = applyUserId;
    }

    public Long getAuthUserId() {
        return authUserId;
    }

    public void setAuthUserId(Long authUserId) {
        this.authUserId = authUserId;
    }

    public String getApplyContent() {
        return applyContent;
    }

    public void setApplyContent(String applyContent) {
        this.applyContent = applyContent == null ? null : applyContent.trim();
    }

    public String getAuthContent() {
        return authContent;
    }

    public void setAuthContent(String authContent) {
        this.authContent = authContent == null ? null : authContent.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Long creatTime) {
        this.creatTime = creatTime;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }
}