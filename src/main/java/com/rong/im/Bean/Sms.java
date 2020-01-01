package com.rong.im.Bean;


public class Sms {

  private long id;
  private String phone;
  private String smcode;
  private long createTime;
  private int status;

  public Sms() {

  }

  public Sms(int id, String smcode, String phone, int createTime) {
    this.id = id;
    this.phone = phone;
    this.smcode = smcode;
    this.createTime = createTime;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getSmcode() {
    return smcode;
  }

  public void setSmcode(String smcode) {
    this.smcode = smcode;
  }


  public long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(long createTime) {
    this.createTime = createTime;
  }

}
