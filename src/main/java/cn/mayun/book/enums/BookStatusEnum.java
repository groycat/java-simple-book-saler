package cn.mayun.book.enums;

public enum BookStatusEnum {
    Normal(0), OffShelf(1), Sold(2),
    ;

    private Integer id;
    BookStatusEnum(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
