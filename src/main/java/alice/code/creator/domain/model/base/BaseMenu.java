package alice.code.creator.domain.model.base;

import alice.code.creator.domain.Transient;
import alice.code.creator.domain.model.AbstractEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单基本信息表Domain
 * User: contact@liuxp.me
 * Date: 2018-06-28
 **/
public class BaseMenu extends AbstractEntity{

    /**
     * 菜单英文名称
     */
    private String menuKey;
    /**
     * 菜单中文名称
     */
    private String menuName;
    /**
     * 菜单描述
     */
    private String menuDescription;
    /**
     * 菜单地址
     */
    private String menuUrl;
    /**
     * 菜单图标
     */
    private String menuIcon;
    /**
     * 菜单类型
     */
    private Integer menuType;
    /**
     * 菜单级别
     */
    private Integer menuLevel;
    /**
     * 菜单顺序
     */
    private Integer menuOrder;
    /**
     * 父级主键
     */
    private Long menuParentId;
    /**
     * 父级菜单名称
     */
    private String menuParentName;
    /**
     * 是否选中
     */
    @Transient
    private Boolean isCheck;
    /**
     * 子菜单
     */
    @Transient
    private List<BaseMenu> childMenus = new ArrayList<>();

    /**
     * 获取菜单英文名称
     * @return 菜单英文名称
     */
    public String getMenuKey() {
        return menuKey;
    }

    /**
     * 设置菜单英文名称
     * @param menuKey 菜单英文名称
     */
    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey;
    }
    /**
     * 获取菜单中文名称
     * @return 菜单中文名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单中文名称
     * @param menuName 菜单中文名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    /**
     * 获取菜单描述
     * @return 菜单描述
     */
    public String getMenuDescription() {
        return menuDescription;
    }

    /**
     * 设置菜单描述
     * @param menuDescription 菜单描述
     */
    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }
    /**
     * 获取菜单地址
     * @return 菜单地址
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * 设置菜单地址
     * @param menuUrl 菜单地址
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
    /**
     * 获取菜单图标
     * @return 菜单图标
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     * 设置菜单图标
     * @param menuIcon 菜单图标
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }
    /**
     * 获取菜单类型
     * @return 菜单类型
     */
    public Integer getMenuType() {
        return menuType;
    }

    /**
     * 设置菜单类型
     * @param menuType 菜单类型
     */
    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }
    /**
     * 获取菜单级别
     * @return 菜单级别
     */
    public Integer getMenuLevel() {
        return menuLevel;
    }

    /**
     * 设置菜单级别
     * @param menuLevel 菜单级别
     */
    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }
    /**
     * 获取菜单顺序
     * @return 菜单顺序
     */
    public Integer getMenuOrder() {
        return menuOrder;
    }

    /**
     * 设置菜单顺序
     * @param menuOrder 菜单顺序
     */
    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }
    /**
     * 获取父级主键
     * @return 父级主键
     */
    public Long getMenuParentId() {
        return menuParentId;
    }

    /**
     * 设置父级主键
     * @param menuParentId 父级主键
     */
    public void setMenuParentId(Long menuParentId) {
        this.menuParentId = menuParentId;
    }
    /**
     * 获取父级菜单名称
     * @return 父级菜单名称
     */
    public String getMenuParentName() {
        return menuParentName;
    }

    /**
     * 设置父级菜单名称
     * @param menuParentName 父级菜单名称
     */
    public void setMenuParentName(String menuParentName) {
        this.menuParentName = menuParentName;
    }

    public Boolean getIsCheck() {
        return this.isCheck;
    }

    public void setIsCheck(Boolean isCheck) {
        this.isCheck = isCheck;
    }

    public List<BaseMenu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<BaseMenu> childMenus) {
        this.childMenus = childMenus;
    }

    public void addChildMenu(BaseMenu baseMenu){
        this.childMenus.add(baseMenu);
    }
}