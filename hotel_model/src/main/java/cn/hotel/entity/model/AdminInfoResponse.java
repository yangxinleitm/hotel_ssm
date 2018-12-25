package cn.hotel.entity.model;

public class AdminInfoResponse extends BaseRequest {
    private Long adminId;
    private String adminName;
    private String adminPwd;
    private String adminRealName;
    private String adminSex;
    private String adminBirthday;
    private String adminNation;
    private Long adminIdCard;
    private Long adminMobile;
    private String address;
    private String adminIdCardType;
    private String adminIsPostion;
    private String adminPostType;
    private Long createTime;
    private Long modifyTime;

    private Long pageSize;
    private Long pageNumber;
    private Long createTimeStart;
    private Long createTimeEnd;

    public Long getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Long createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Long getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Long createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public AdminInfoResponse() {
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    public String getAdminRealName() {
        return adminRealName;
    }

    public void setAdminRealName(String adminRealName) {
        this.adminRealName = adminRealName;
    }

    public String getAdminSex() {
        return adminSex;
    }

    public void setAdminSex(String adminSex) {
        this.adminSex = adminSex;
    }

    public String getAdminBirthday() {
        return adminBirthday;
    }

    public void setAdminBirthday(String adminBirthday) {
        this.adminBirthday = adminBirthday;
    }

    public String getAdminNation() {
        return adminNation;
    }

    public void setAdminNation(String adminNation) {
        this.adminNation = adminNation;
    }

    public Long getAdminIdCard() {
        return adminIdCard;
    }

    public void setAdminIdCard(Long adminIdCard) {
        this.adminIdCard = adminIdCard;
    }

    public Long getAdminMobile() {
        return adminMobile;
    }

    public void setAdminMobile(Long adminMobile) {
        this.adminMobile = adminMobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdminIdCardType() {
        return adminIdCardType;
    }

    public void setAdminIdCardType(String adminIdCardType) {
        this.adminIdCardType = adminIdCardType;
    }

    public String getAdminIsPostion() {
        return adminIsPostion;
    }

    public void setAdminIsPostion(String adminIsPostion) {
        this.adminIsPostion = adminIsPostion;
    }

    public String getAdminPostType() {
        return adminPostType;
    }

    public void setAdminPostType(String adminPostType) {
        this.adminPostType = adminPostType;
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
        return "AdminRequest{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", adminPwd='" + adminPwd + '\'' +
                ", adminRealName='" + adminRealName + '\'' +
                ", adminSex='" + adminSex + '\'' +
                ", adminBirthday='" + adminBirthday + '\'' +
                ", adminNation='" + adminNation + '\'' +
                ", adminIdCard=" + adminIdCard +
                ", adminMobile=" + adminMobile +
                ", address='" + address + '\'' +
                ", adminIdCardType='" + adminIdCardType + '\'' +
                ", adminIsPostion='" + adminIsPostion + '\'' +
                ", adminPostType='" + adminPostType + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                '}';
    }
}

