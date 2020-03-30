package com.example.a2048_mobile;

public class Box {
    private int rowIndex;
    private int colIndex;
    private int num;

    public Box(int row,int col)
    {
        this.rowIndex=row;
        this.colIndex=col;
        this.num=0;
    }

    public int getRowIndex() { return rowIndex; }

    public void setRowIndex(int rowIndex) { this.rowIndex = rowIndex; }

    public int getColsIndex() { return colIndex; }

    public void setColsIndex(int colsIndex) { this.colIndex = colsIndex; }

    public void Reset() { this.num=0; }

    public int getNum() { return num; }

    public void setNum(int num) { this.num = num; }
}
