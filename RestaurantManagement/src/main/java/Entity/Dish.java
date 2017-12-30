package Entity;
// Generated Dec 30, 2017 3:24:26 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Dish generated by hbm2java
 */
public class Dish  implements java.io.Serializable {


     private Integer id;
     private int branchId;
     private int dishDirectoryId;
     private String description;
     private String name;
     private String imgUrl;
     private double price;
     private Date createdAt;
     private int delFlag;

    public Dish() {
    }

    public Dish(int branchId, int dishDirectoryId, String description, String name, String imgUrl, double price, Date createdAt, int delFlag) {
       this.branchId = branchId;
       this.dishDirectoryId = dishDirectoryId;
       this.description = description;
       this.name = name;
       this.imgUrl = imgUrl;
       this.price = price;
       this.createdAt = createdAt;
       this.delFlag = delFlag;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public int getBranchId() {
        return this.branchId;
    }
    
    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
    public int getDishDirectoryId() {
        return this.dishDirectoryId;
    }
    
    public void setDishDirectoryId(int dishDirectoryId) {
        this.dishDirectoryId = dishDirectoryId;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getImgUrl() {
        return this.imgUrl;
    }
    
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public double getPrice() {
        return this.price;
    }
    
    public void setPrice(double price) {
        this.price = price;
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


