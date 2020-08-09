package com.xtm.test1.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 项目、产品表
 * </p>
 *
 * @author xtm
 * @since 2020-07-08
 */
public class SProject extends Model<SProject> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @TableField("projectName")
    private String projectName;


    /**
     * 负责人id
     */
    @TableField("dutyPsnId")
    private Integer dutyPsnId;

    /**
     * 计划开始时间
     */
    @TableField("pStartDate")
    protected LocalDate pStartDate;

    /**
     * 计划结束时间
     */
    @TableField("pEndDate")
    protected LocalDate pEndDate;

    /**
     * 实际开始时间
     */
    @TableField("rStartDate")
    protected LocalDate rStartDate;

    /**
     * 实际结束时间
     */
    @TableField("rEndDate")
    protected LocalDate rEndDate;

    /**
     * 父节点
     */
    @TableField("parentId")
    private Integer parentId;

    /**
     * 说明
     */
    private String remark;

    /**
     * 甲方单位
     */
    @TableField("deptName")
    private String deptName;

    /**
     * 状态(未启动、实施中、已结束、已取消)
     */
    protected String status;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    @TableField("createPsnId")
    private Integer createPsnId;

    @Version
    private Integer version;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getDutyPsnId() {
        return dutyPsnId;
    }

    public void setDutyPsnId(Integer dutyPsnId) {
        this.dutyPsnId = dutyPsnId;
    }

    public LocalDate getpStartDate() {
        return pStartDate;
    }

    public void setpStartDate(LocalDate pStartDate) {
        this.pStartDate = pStartDate;
    }

    public LocalDate getpEndDate() {
        return pEndDate;
    }

    public void setpEndDate(LocalDate pEndDate) {
        this.pEndDate = pEndDate;
    }

    public LocalDate getrStartDate() {
        return rStartDate;
    }

    public void setrStartDate(LocalDate rStartDate) {
        this.rStartDate = rStartDate;
    }

    public LocalDate getrEndDate() {
        return rEndDate;
    }

    public void setrEndDate(LocalDate rEndDate) {
        this.rEndDate = rEndDate;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getCreatePsnId() {
        return createPsnId;
    }

    public void setCreatePsnId(Integer createPsnId) {
        this.createPsnId = createPsnId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
