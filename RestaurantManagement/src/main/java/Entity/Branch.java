package Entity;
// Generated Nov 27, 2017 11:23:00 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Branch generated by hbm2java
 */
public class Branch  implements java.io.Serializable {


     private Integer id;
     private String name;
     private String address;
     private int directoryId;
     private Date createdAt;
     private int delFlag;

    public Branch() {
    }

    public Branch(String name, String address, int directoryId, Date createdAt, int delFlag) {
       this.name = name;
       this.address = address;
       this.directoryId = directoryId;
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
    public int getDirectoryId() {
        return this.directoryId;
    }
    
    public void setDirectoryId(int directoryId) {
        this.directoryId = directoryId;
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


