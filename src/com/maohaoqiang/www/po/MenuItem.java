package com.maohaoqiang.www.po;

public class MenuItem {
    private Menu menu;
    private int count;

    public MenuItem(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }
    public MenuItem(){
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
