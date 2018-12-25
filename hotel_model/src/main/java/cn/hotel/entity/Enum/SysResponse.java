package cn.hotel.entity.Enum;



public class SysResponse {
    public static enum RECORD_CODE {

        RESPONSE_SUCCESS(200),                  //成功
        RESPONSE_FAIL(300),                     //失败
        PARAM_ISIMPTY(1000);                    //传入的参数不能为空

        private Integer type;

        private RECORD_CODE(Integer type) {
            this.type = type;
        }

        public Integer get() {
            return this.type;
        }
    }

    public static enum RECORD_MESSAGE {

        RESPONSE_SUCCESS_MESSAGE("成功"),
        RESPONSE_FAIL_MESSAGE("失败"),
        PARAM_ISIMPTY_MESSAGE("参数不能为空");


        private String type;

        private RECORD_MESSAGE(String type) {
            this.type = type;
        }

        public String get() {
            return this.type;
        }
    }

}
