package com.ccccit.spring.boot.entity;

public class ExcelError {
    private int rowNo;
    private int colNo;
    private String message;

    public ExcelError(){}
    public ExcelError(int rowNo, int colNo, String message){
        this.rowNo = rowNo;
        this.colNo = colNo;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "rowNo=" + rowNo +
                ", colNo=" + colNo +
                ", message=<span style=\"color:red\">" + message + "</span>";
    }
}
