package alice.code.creator.domain.model.base;

import alice.code.creator.domain.Transient;
import alice.code.creator.domain.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单基本信息表Domain
 * User: contact@liuxp.me
 * Date: 2018-06-28
 **/
@Setter
@Getter
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

    public void addChildMenu(BaseMenu baseMenu){
        this.childMenus.add(baseMenu);
    }
}