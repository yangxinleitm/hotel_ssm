package cn.hotel.entity.model;

import java.io.Serializable;

public class CustomerInfoRequest extends BaseRequest{
    private Long userId;
    private String userName;
    private String userPassword;
    private String userRealName;
    private String isVip;
    private String usrSex;
    private String userBirthday;
    private String userNation;
    private Long  userIdCard;
    private Long  userMobile;
    private String address;
    private String userIdCardType;
    private String userIspostion;
    private String postType;
    private Long createTime;
    private Long modifyTime;

    public CustomerInfoRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    public String getUsrSex() {
        return usrSex;
    }

    public void setUsrSex(String usrSex) {
        this.usrSex = usrSex;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserNation() {
        return userNation;
    }

    public void setUserNation(String userNation) {
        this.userNation = userNation;
    }

    public Long getUserIdCard() {
        return userIdCard;
    }

    public void setUserIdCard(Long userIdCard) {
        this.userIdCard = userIdCard;
    }

    public Long getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(Long userMobile) {
        this.userMobile = userMobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserIdCardType() {
        return userIdCardType;
    }

    public void setUserIdCardType(String userIdCardType) {
        this.userIdCardType = userIdCardType;
    }

    public String getUserIspostion() {
        return userIspostion;
    }

    public void setUserIspostion(String userIspostion) {
        this.userIspostion = userIspostion;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
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
        return "CustomerDto{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", isVip='" + isVip + '\'' +
                ", usrSex='" + usrSex + '\'' +
                ", userBirthday='" + userBirthday + '\'' +
                ", userNation='" + userNation + '\'' +
                ", userIdCard=" + userIdCard +
                ", userMobile=" + userMobile +
                ", address='" + address + '\'' +
                ", userIdCardType='" + userIdCardType + '\'' +
                ", userIspostion='" + userIspostion + '\'' +
                ", postType='" + postType + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
