package Entity;
// Generated Dec 30, 2017 3:24:26 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Customer generated by hbm2java
 */
public class Customer  implements java.io.Serializable {


     private Integer id;
     private String name;
     private String address;
     private String phone;
     private Date createdAt;
     private int delFlag;

    public Customer() {
    }

    public Customer(String name, String address, String phone, Date createdAt, int delFlag) {
       this.name = name;
       this.address = address;
       this.phone = phone;
       this.createdAt = createdAt;
       this.delFlag = delFlag;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Date getCreatedAt() {
        return this.createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public int getDelFlag() {
        return this.delFlag;
    }
    
    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }




}


