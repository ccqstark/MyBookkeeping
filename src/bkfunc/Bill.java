package bkfunc;

public class Bill {

    private int bill_id;
    private String date;
    private String cate;
    private String pro;
    private String description;
    private float money;

    public Bill(int bill_id, String date,String cate,String pro,String description,float money){
        this.bill_id = bill_id;
        this.date = date;
        this.cate = cate;
        this.pro = pro;
        this.description = description;
        this.money = money;
    }

    public int getBill_id() {
        return bill_id;
    }

    public String getDate() {
        return date;
    }

    public String getCate() {
        return cate;
    }

    public String getPro() {
        return pro;
    }

    public String getDescription() {
        return description;
    }

    public float getMoney() {
        return money;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "bill_id=" + bill_id +
                ", date='" + date + '\'' +
                ", cate='" + cate + '\'' +
                ", pro='" + pro + '\'' +
                ", description='" + description + '\'' +
                ", money=" + money +
                '}';
    }
}

