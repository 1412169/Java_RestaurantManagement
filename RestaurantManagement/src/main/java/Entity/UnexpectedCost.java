package Entity;
// Generated Dec 30, 2017 3:24:26 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * UnexpectedCost generated by hbm2java
 */
public class UnexpectedCost  implements java.io.Serializable {


     private Integer id;
     private int branchId;
     private String content;
     private double cost;
     private Date createdAt;
     private int delFlag;

    public UnexpectedCost() {
    }

    public UnexpectedCost(int branchId, String content, double cost, Date createdAt, int delFlag) {
       this.branchId = branchId;
       this.content = content;
       this.cost = cost;
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
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    public double getCost() {
        return this.cost;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
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


