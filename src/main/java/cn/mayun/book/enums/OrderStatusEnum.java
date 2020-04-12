package cn.mayun.book.enums;

public enum OrderStatusEnum {
    UnConfirm(0), Confirm(1), Done(2),
            ;

    private Integer id;
    OrderStatusEnum(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
