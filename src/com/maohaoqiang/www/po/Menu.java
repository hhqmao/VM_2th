package com.maohaoqiang.www.po;


public class Menu {
   private int id;
   private String view;
   private String menuname;
   private String from;
   private  int cash;
   private  int number;

    public Menu() {
    }

    public Menu(String menuname, String from, int cash, int number, String view) {
        this.menuname = menuname;
        this.from = from;
        this.view=view;
        this.number = number;
        this.cash = cash;
    }

    public Menu(String menuname, String from, int cash, int number) {
        this.menuname = menuname;
        this.from = from;
        this.cash = cash;
        this.number = number;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
