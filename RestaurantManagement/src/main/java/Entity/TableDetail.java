package Entity;
// Generated Dec 30, 2017 3:24:26 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * TableDetail generated by hbm2java
 */
public class TableDetail  implements java.io.Serializable {


     private Integer id;
     private int customerId;
     private int tableId;
     private Date time;
     private Date createdAt;
     private int delFlag;

    public TableDetail() {
    }

    public TableDetail(int customerId, int tableId, Date time, Date createdAt, int delFlag) {
       this.customerId = customerId;
       this.tableId = tableId;
       this.time = time;
       this.createdAt = createdAt;
       this.delFlag = delFlag;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public int getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public int getTableId() {
        return this.tableId;
    }
    
    public void setTableId(int tableId) {
        this.tableId = tableId;
    }
    public Date getTime() {
        return this.time;
    }
    
    public void setTime(Date time) {
        this.time = time;
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


