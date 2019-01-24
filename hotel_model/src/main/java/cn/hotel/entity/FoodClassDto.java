package cn.hotel.entity;

public class FoodClassDto {
    private Long foodClassId;

    private String foodClassNo;

    private String foodSubjectId;


    private String foodClassName;

    private Long applyUserId;

    private Long authUserId;

    private Integer isDelete;

    private Long createTime;

    private Long modifyTime;

    public Long getFoodClassId() {
        return foodClassId;
    }

    public void setFoodClassId(Long foodClassId) {
        this.foodClassId = foodClassId;
    }

    public String getFoodClassNo() {
        return foodClassNo;
    }

    public void setFoodClassNo(String foodClassNo) {
        this.foodClassNo = foodClassNo == null ? null : foodClassNo.trim();
    }

    public String getFoodSubjectId() {
        return foodSubjectId;
    }

    public void setFoodSubjectId(String foodSubjectId) {
        this.foodSubjectId = foodSubjectId;
    }


    public String getFoodClassName() {
        return foodClassName;
    }

    public void setFoodClassName(String foodClassName) {
        this.foodClassName = foodClassName == null ? null : foodClassName.trim();
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
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
}