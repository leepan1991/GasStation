package com.volunteer.model;

/**
 * Created by Administrator on 2017/9/7 0007.
 */
public class GasBottleUpdateCheckTime {
    private int[] ids;
    private String nextCheckDate;

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    public String getNextCheckDate() {
        return nextCheckDate;
    }

    public void setNextCheckDate(String nextCheckDate) {
        this.nextCheckDate = nextCheckDate;
    }
}
