package alice.code.creator.domain.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import alice.code.creator.domain.Transient;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * 数据实体基类
 * @author contact@liuxp.me
 */
public abstract class AbstractEntity implements Serializable {

    /**
     * 数据未删除
     */
    public static final int NOT_DELETED = 0;
    /**
     * 数据已删除
     */
    public static final int DELETED = 1;
    /**
     * 数据启用
     */
    public static final int ENABLE = 1;
    /**
     * 数据停用
     */
    public static final int NOT_ENABLE = 0;

    // 序列化
    private static final long serialVersionUID = 1L;

    // 主键
    private Long id;

    // 主键最大值（用于查询）
    @Transient
    private Long idMax;

    // 主键最小值（用于查询）
    @Transient
    private Long idMin;

    // 创建人
    private String createUser;

    // 创建时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    // 创建时间/起(虚拟字段：用于时间段查询)
    @Transient
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeBegin;

    // 创建时间/止(虚拟字段：用于时间段查询)
    @Transient
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeEnd;

    // 修改人
    private String updateUser;

    // 修改时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    // 修改时间/起(虚拟字段：用于时间段查询)
    @Transient
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTimeBegin;

    // 修改时间/止(虚拟字段：用于时间段查询)
    @Transient
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTimeEnd;

    // 逻辑删除标志
    private Integer isDelete;

    /**
     * 取得主键
     * @return 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 取得主键最大值查询条件（不包括该值）
     * @return 主键最大值查询条件（不包括该值）
     */
    public Long getIdMax() {
        return idMax;
    }

    /**
     * 设置主键最大值查询条件（不包括该值）
     * @param idMax 主键最大值查询条件（不包括该值）
     */
    public void setIdMax(Long idMax) {
        this.idMax = idMax;
    }

    /**
     * 取得主键最小值查询条件（不包括该值）
     * @return 主键最小值查询条件（不包括该值）
     */
    public Long getIdMin() {
        return idMin;
    }

    /**
     * 设置主键最小值查询条件（不包括该值）
     * @param idMin 主键最小值查询条件（不包括该值）
     */
    public void setIdMin(Long idMin) {
        this.idMin = idMin;
    }

    /**
     * 取得创建人
     * @return 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 取得创建时间
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得修改人
     * @return 修改人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置修改人
     * @param updateUser 修改人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 取得修改时间
     * @return 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 取得创建日期开始查询条件（包括该时间）
     * @return 创建日期开始查询条件（包括该时间）
     */
    public Date getCreateTimeBegin() {
        return createTimeBegin;
    }

    /**
     * 设置创建日期开始查询条件（包括该时间）
     * @param createTimeBegin 创建日期开始查询条件（包括该时间）
     */
    public void setCreateTimeBegin(Date createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    /**
     * 取得创建日期结束查询条件（包括该时间）
     * @return 创建日期结束查询条件（包括该时间）
     */
    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    /**
     * 设置创建日期结束查询条件（包括该时间）
     * @param createTimeEnd 创建日期结束查询条件（包括该时间）
     */
    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    /**
     * 取得修改日期开始查询条件（包括该时间）
     * @return 修改日期开始查询条件（包括该时间）
     */
    public Date getUpdateTimeBegin() {
        return updateTimeBegin;
    }

    /**
     * 设置修改日期开始查询条件（包括该时间）
     * @param updateTimeBegin 修改日期开始查询条件（包括该时间）
     */
    public void setUpdateTimeBegin(Date updateTimeBegin) {
        this.updateTimeBegin = updateTimeBegin;
    }

    /**
     * 取得修改日期结束查询条件（包括该时间）
     * @return 修改日期结束查询条件（包括该时间）
     */
    public Date getUpdateTimeEnd() {
        return updateTimeEnd;
    }

    /**
     * 设置修改日期结束查询条件（包括该时间）
     * @param updateTimeEnd 修改日期结束查询条件（包括该时间）
     */
    public void setUpdateTimeEnd(Date updateTimeEnd) {
        this.updateTimeEnd = updateTimeEnd;
    }

    /**
     * 取得是否删除
     * @return 是否删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除
     * @param isDelete 是否删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 判断属性是否有@Transient注解
     *
     * @param clazz 类
     * @param propertyName 属性名
     * @return 是否有@Transient注解
     */
    private boolean isTransient(Class<?> clazz, String propertyName) {
        // 取得基类Field
        Field[] fidldsOfSuperClazz = AbstractEntity.class.getDeclaredFields();
        // 取得子类Field
        Field[] fidldsOfClazz = clazz.getDeclaredFields();
        // 首先在基类中判断属性是否有@Transient注解
        for (Field field : fidldsOfSuperClazz) {
            if (field.getName().equals(propertyName) && field.isAnnotationPresent(Transient.class)) {
                return true;
            }
        }
        // 其次在子类中判断属性是否有@Transient注解
        for (Field field : fidldsOfClazz) {
            if (field.getName().equals(propertyName) && field.isAnnotationPresent(Transient.class)) {
                return true;
            }
        }
        return false;
    }

}
