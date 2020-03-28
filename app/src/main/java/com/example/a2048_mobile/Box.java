package com.example.a2048_mobile;

public class Box {
    private int rowIndex;
    private int colsIndex;
    private int num;

    public Box(int row,int cols)
    {
        this.rowIndex=row;
        this.colsIndex=cols;
        this.num=0;
    }

    public void Reset()
    {
        this.num=0;
    }
    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColsIndex() {
        return colsIndex;
    }

    public void setColsIndex(int colsIndex) {
        this.colsIndex = colsIndex;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
