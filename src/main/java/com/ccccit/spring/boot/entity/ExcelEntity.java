package com.ccccit.spring.boot.entity;

public class ExcelEntity {
    private int rowNo;
    private int colNo;
    private Object value;
    public ExcelEntity(int rowNo, int colNo, Object value){
        this.rowNo = rowNo;
        this.colNo = colNo;
        this.value = value;
    }
    public int getRowNo() {
        return rowNo;
    }

    public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }

    public int getColNo() {
        return colNo;
    }

    public void setColNo(int colNo) {
        this.colNo = colNo;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
