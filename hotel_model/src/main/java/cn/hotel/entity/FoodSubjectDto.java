package cn.hotel.entity;

public class FoodSubjectDto {
    private String foodSubjectId;

    private String foodSubjectNo;

    private String foodSubjectName;

    private String foodId;

    private Long applyUserId;

    private Long authUserId;

    private Integer isDelete;

    private Long createTime;

    private Long modifyTime;

    public String getFoodSubjectId() {
        return foodSubjectId;
    }

    public void setFoodSubjectId(String foodSubjectId) {
        this.foodSubjectId = foodSubjectId == null ? null : foodSubjectId.trim();
    }

    public String getFoodSubjectNo() {
        return foodSubjectNo;
    }

    public void setFoodSubjectNo(String foodSubjectNo) {
        this.foodSubjectNo = foodSubjectNo == null ? null : foodSubjectNo.trim();
    }

    public String getFoodSubjectName() {
        return foodSubjectName;
    }

    public void setFoodSubjectName(String foodSubjectName) {
        this.foodSubjectName = foodSubjectName == null ? null : foodSubjectName.trim();
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId == null ? null : foodId.trim();
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