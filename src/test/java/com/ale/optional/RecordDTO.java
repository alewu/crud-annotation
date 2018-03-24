package com.ale.optional;
/**
  * @author alewu
  * @date 2018/3/22
  * @description 记录
  */
public class RecordDTO {
    /** 类型:1：浏览， 2：转发 */
    private Integer type;
    /** 次数 */
    private Integer time;
    /** 头像 */
    private String headImgUrl;

    public RecordDTO(Integer type, Integer time, String headImgUrl){
        this.time = time;
        this.type = type;
        this.headImgUrl = headImgUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    @Override
    public String toString() {
        return "RecordDTO{" +
                "type=" + type +
                ", time=" + time +
                ", headImgUrl='" + headImgUrl + '\'' +
                '}';
    }
}
