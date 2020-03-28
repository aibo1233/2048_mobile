package com.example.a2048_mobile;

import java.util.Random;

public class Grid {
    private int row;
    private int cols;
    public Box[][] Boxes;
    private int gridnum=2;
    private boolean isFirst=true;
    private Random r;
    public boolean isGameOver=false;
    private int total=0;

    public Grid(int row,int cols)
    {
        this.row = row < 4 ? 4 : row;
        this.cols = cols < 4 ? 4 : cols;
        Boxes = new Box[row][cols];
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<cols;j++)
            {
                Boxes[i][j]=new Box(i,j);
            }
        }
        r=new Random();
    }

    public void InitGrid()
    {
        for (int i = 0; i < Boxes.length; i++)
        {
            for (int j = 0; j < Boxes[0].length; j++)
            {
                Boxes[i][j].Reset();
            }
        }
        this.isFirst = true;
        this.isGameOver = false;
        this.total = 0;
        this.Creat_RandomNum();
    }

    public void Creat_RandomNum()//生成随机数
    {
        int count = 0;
        if(isFirst)
        {
            count = 2;
            isFirst = false;
        }
        else
        {
            count = 1;
        }
        for (int i = 0; i < count;)
        {
            int rr = r.nextInt(row);
            int rc = r.nextInt(cols);
            if (Boxes[rr][rc].getNum() != 0)
            {
                continue;
            }
            if(gridnum==4)
            {
                gridnum = 2;
            }
            else if(gridnum==2)
            {
                int num = r.nextInt(10);
                if(num<8)
                {
                    gridnum = 2;
                }
                else
                {
                    gridnum = 4;
                }
            }
            Boxes[rr][rc].setNum(gridnum);
            i++;
        }
        total += count;
    }

    public void Move_Right()//向右划
    {
        boolean changed = false;
        for(int i=0;i<Boxes.length;i++)
        {
            boolean real = false;
            for (int j=0;j<Boxes[0].length;j++)
            {
                if(Boxes[i][j].getNum()!=0)
                {
                    real = true;
                }
                    else
                {
                    if(real==true)
                    {
                        changed = true;
                        for(int k=j;k>0;k--)
                        {
                            Boxes[i][k].setNum(Boxes[i][ k - 1].getNum());
                        }
                        Boxes[i][ 0].setNum(0);
                    }
                }
            }
        }
        for (int i = 0; i < Boxes.length; i++)
        {
            for (int j = Boxes[0].length - 1; j >0 ; j--)
            {
                if (Boxes[i][ j].getNum() == Boxes[i][ j - 1].getNum() && Boxes[i][ j].getNum() != 0)
                {
                    total -= 1;
                    changed = true;
                    Boxes[i][ j].setNum(Boxes[i][ j].getNum()*2);
                    for (int k = j-1; k > 0; k--)
                    {
                        Boxes[i][ k].setNum(Boxes[i][ k - 1].getNum());
                    }
                    Boxes[i][ 0].setNum(0);
                }
            }
        }
        if (changed==true)
        {
            this.Creat_RandomNum();
        }
    }

    public void Move_Up()//向上划
    {
        boolean changed = false;
        for (int i = 0; i < Boxes[0].length; i++)
        {
            boolean real = false;
            for (int j = Boxes.length-1; j>=0 ; j--)
            {
                if (Boxes[j][i].getNum() != 0)
                {
                    real = true;
                }
                    else
                {
                    if (real == true)
                    {
                        changed = true;
                        for (int k = j; k < Boxes.length - 1; k++)
                        {
                            Boxes[k][i].setNum(Boxes[ k + 1][i].getNum());
                        }
                        Boxes[ Boxes.length - 1][i].setNum(0);
                    }
                }
            }
        }
        for (int i = 0; i < Boxes[0].length; i++)
        {
            for (int j = 0; j < Boxes.length - 1; j++)
            {
                if (Boxes[j][ i].getNum() == Boxes[j+1][i].getNum() && Boxes[j][ i].getNum() != 0)
                {
                    total -= 1;
                    changed = true;
                    Boxes[j][i].setNum(Boxes[j][i].getNum()*2);
                    for (int k = j+1; k < Boxes.length - 1; k++)
                    {
                        Boxes[k][i].setNum(Boxes[k + 1][i].getNum());
                    }
                    Boxes[Boxes.length - 1][i].setNum(0);
                }
            }
        }
        if (changed == true)
        {
            this.Creat_RandomNum();
        }
    }
    public void Move_Left()//向左划
    {
        boolean changed = false;
        for (int i = 0; i < Boxes.length; i++)
        {
            boolean real = false;
            for (int j = Boxes[0].length - 1; j >= 0; j--)
            {
                if (Boxes[i][ j].getNum() != 0)
                {
                    real = true;
                }
                    else
                {
                    if (real == true)
                    {
                        changed = true;
                        for (int k = j; k < Boxes[0].length - 1; k++)
                        {
                            Boxes[i][k].setNum(Boxes[i][ k + 1].getNum());
                        }
                        Boxes[i][ Boxes[0].length - 1].setNum(0);
                    }
                }
            }
        }
        for (int i = 0; i < Boxes.length; i++)
        {
            for (int j = 0; j < Boxes[0].length - 1; j++)
            {
                if (Boxes[i][ j].getNum() == Boxes[i][ j + 1].getNum() && Boxes[i][ j].getNum() != 0)
                {
                    total -= 1;
                    changed = true;
                    Boxes[i][ j].setNum(Boxes[i][ j].getNum()*2);
                    for (int k = j+1; k < Boxes[0].length - 1; k++)
                    {
                        Boxes[i][ k].setNum(Boxes[i][ k + 1].getNum());
                    }
                    Boxes[i][ Boxes[0].length - 1].setNum(0);
                }
            }
        }
        if (changed == true)
        {
            this.Creat_RandomNum();
        }
    }
    public void Move_Down()//向下划
    {
        boolean changed = false;
        for (int i = 0; i < Boxes[0].length; i++)
        {
            boolean real = false;
            for (int j = 0; j < Boxes.length; j++)
            {
                if (Boxes[j][i].getNum() != 0)
                {
                    real = true;
                }
                    else
                {
                    if (real == true)
                    {
                        changed = true;
                        for (int k = j; k > 0; k--)
                        {
                            Boxes[k][i].setNum(Boxes[k-1][i].getNum());
                        }
                        Boxes[0][i].setNum(0);
                    }
                }
            }
        }
        for (int i = 0; i < Boxes[0].length; i++)
        {
            for (int j = Boxes.length - 1; j >0 ; j--)
            {
                if (Boxes[j][i].getNum() == Boxes[j-1][i].getNum() && Boxes[j][i].getNum() != 0)
                {
                    total -= 1;
                    changed = true;
                    Boxes[j][i].setNum(Boxes[j][i].getNum()*2);
                    for (int k = j-1; k > 0; k--)
                    {
                        Boxes[k][i].setNum(Boxes[k-1][i].getNum());
                    }
                    Boxes[0][i].setNum(0);
                }
            }
        }
        if (changed == true)
        {
            this.Creat_RandomNum();
        }
    }
    public void CheckGameisOver()//判断游戏是否结束
    {
        if(total<row*cols)
        {
            isGameOver = false;
            return;
        }
        for(int i=0;i<Boxes.length;i++)
        {
            for(int j=0;j<Boxes[0].length-1;j++)
            {
                if(Boxes[i][j].getNum()==Boxes[i][j+1].getNum())
                {
                    isGameOver = false;
                    return;
                }
                if (Boxes[j][i].getNum() == Boxes[j+1][i].getNum())
                {
                    isGameOver = false;
                    return;
                }
            }
        }
        isGameOver = true;
    }
}
