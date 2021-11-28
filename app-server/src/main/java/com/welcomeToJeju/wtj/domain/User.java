package com.welcomeToJeju.wtj.domain;

import java.sql.Date;

public class User implements Comparable<User> {

  private int no;
  private String email;
  private String password;
  private String nickname;
  private Date registeredDate;

  private String emoji;

  private int viewCount;
  private int active;

  @Override
  public String toString() {
    return "User [no=" + no + ", email=" + email + ", password=" + password + ", nickname="
        + nickname + ", registeredDate=" + registeredDate + ", emoji=" + emoji + ", viewCount="
        + viewCount + ", active=" + active + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

  public String getEmoji() {
    return emoji;
  }

  public void setEmoji(String emoji) {
    this.emoji = emoji;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public int getActive() {
    return active;
  }

  public void setActive(int active) {
    this.active = active;
  }

  @Override
  public int compareTo(User user) {
    return user.viewCount - this.viewCount;
  }


}