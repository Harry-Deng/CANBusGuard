package com.dengemo.TekWulf.CANBusGuardian;

/**
 * 一个StandardItem对应ListView中的一个标准项
 * 四个属性对应 Item_Standard.xml 的四个组件
 */
public class StandardItem {
    private String name;
    private String desc;
    private String brief;
    //普通项的右方图标一直是 右箭头
    public static final int RIGHT_ICON = R.drawable.right_arrow;


    /**
     * 创建一个 文本+右箭头 的项
     */
    public StandardItem(String name) {
        this.name = name;
    }

    /**
     * 创建一个 文本+概要+右箭头 的项
     */
    public StandardItem(String name, String brief) {
        this.brief = brief;
        this.name = name;
    }

    /**
     * 创建一个 文本+概要+右箭头 +详情 的项
     */
    public StandardItem(String name, String desc, String brief) {
        this.name = name;
        this.desc = desc;
        this.brief = brief;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDesc() {
        return desc;
    }

    public String getBrief() {
        return brief;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getRightIcon() {
        return RIGHT_ICON;
    }
}
