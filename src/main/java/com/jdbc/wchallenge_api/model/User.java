package com.jdbc.wchallenge_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@Document(collection = "user")
public class User implements Serializable {

  private static final long serialVersionUID = -33943447440291629L;

  @Transient
  public static final String SEQUENCE_NAME = "user_sequence";

  @Id
  private int id;

  private String name;
  private String username;
  private String email;
  private Address address;
  private String phone;
  private String website;
  private Company company;

  public User() {
  }

  public User(int id, String name, String username, String email, Address address, String phone, String website, Company company) {
    this.id = id;
    this.name = name;
    this.username = username;
    this.email = email;
    this.address = address;
    this.phone = phone;
    this.website = website;
    this.company = company;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return id == user.id &&
            name.equals(user.name) &&
            username.equals(user.username) &&
            email.equals(user.email) &&
            phone.equals(user.phone) &&
            Objects.equals(website, user.website) &&
            Objects.equals(address, user.address) &&
            Objects.equals(company, user.company);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, username, email, phone, website, address, company);
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            ", website='" + website + '\'' +
            ", address=" + address +
            ", company=" + company +
            '}';
  }

  public class Address implements Serializable {

    private static final long serialVersionUID = -7918978892444333289L;
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    public Address() {}

    @PersistenceConstructor
    public Address(String street, String suite, String city, String zipcode) {
      this.street = street;
      this.suite = suite;
      this.city = city;
      this.zipcode = zipcode;
    }

    public String getStreet() {
      return street;
    }

    public void setStreet(String street) {
      this.street = street;
    }

    public String getSuite() {
      return suite;
    }

    public void setSuite(String suite) {
      this.suite = suite;
    }

    public String getCity() {
      return city;
    }

    public void setCity(String city) {
      this.city = city;
    }

    public String getZipcode() {
      return zipcode;
    }

    public void setZipcode(String zipcode) {
      this.zipcode = zipcode;
    }

    public Geo getGeo() {
      return geo;
    }

    public void setGeo(Geo geo) {
      this.geo = geo;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Address oAddress = (Address) o;
      return street.equals(oAddress.street) &&
              suite.equals(oAddress.suite) &&
              city.equals(oAddress.city) &&
              zipcode.equals(oAddress.zipcode);
    }

    @Override
    public int hashCode() {
      return Objects.hash(street, suite, city, zipcode);
    }

    @Override
    public String toString() {
      return "Address{" +
              "street='" + street + '\'' +
              ", suite='" + suite + '\'' +
              ", city='" + city + '\'' +
              ", zipcode='" + zipcode + '\'' +
              '}';
    }

    public class Geo implements Serializable {

      private static final long serialVersionUID = 2856169849232593385L;
      private String lat;
      private String lng;

      public Geo() {}

      @PersistenceConstructor
      public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
      }

      public String getLat() {
        return lat;
      }

      public void setLat(String lat) {
        this.lat = lat;
      }

      public String getLng() {
        return lng;
      }

      public void setLng(String lng) {
        this.lng = lng;
      }

      @Override
      public String toString() {
        return "Geo{" +
                "lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
      }
    }
  }

  public class Company implements Serializable {

    private static final long serialVersionUID = 7810623847616837548L;
    private String name;
    private String catchPhrase;
    private String bs;

    public Company() {}

    @PersistenceConstructor
    public Company(String name, String catchPhrase, String bs) {
      this.name = name;
      this.catchPhrase = catchPhrase;
      this.bs = bs;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getCatchPhrase() {
      return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
      this.catchPhrase = catchPhrase;
    }

    public String getBs() {
      return bs;
    }

    public void setBs(String bs) {
      this.bs = bs;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Company oCompany = (Company) o;
      return name.equals(oCompany.name) &&
              catchPhrase.equals(oCompany.catchPhrase) &&
              bs.equals(oCompany.bs);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, catchPhrase, bs);
    }

    @Override
    public String toString() {
      return "Company{" +
              "name='" + name + '\'' +
              ", catchPhrase='" + catchPhrase + '\'' +
              ", bs='" + bs + '\'' +
              '}';
    }
  }
}
