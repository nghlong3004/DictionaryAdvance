package model.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
  private String email;
  private String password;
  private String fullname;
  private int gender;
  private LocalDate birthday;
  private LocalDateTime created;
  private LocalDateTime updated;

  public User(String email, String password, String fullname, int gender, LocalDate birthday,
      LocalDateTime registrationDate, LocalDateTime lastLogin) {
    this.email = email;
    this.password = password;
    this.fullname = fullname;
    this.gender = gender;
    this.birthday = birthday;
    this.created = registrationDate;
    this.updated = lastLogin;
  }

  public User() {

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

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public int getGender() {
    return gender;
  }

  public void setGender(short gender) {
    this.gender = gender;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }


  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public LocalDateTime getUpdated() {
    return updated;
  }

  public void setUpdated(LocalDateTime updated) {
    this.updated = updated;
  }

  public List<Object> getAttributesAsList() {
    List<Object> attributes = new ArrayList<>();
    attributes.add(getEmail());
    attributes.add(getPassword());
    attributes.add(getFullname());
    attributes.add(getGender());
    attributes.add(getBirthday());
    attributes.add(getCreated());
    attributes.add(getUpdated());
    return attributes;
  }


}
