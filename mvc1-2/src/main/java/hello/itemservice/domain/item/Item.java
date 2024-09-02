package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {

    /**
     * 상품 ID
     * 상품명
     * 가격
     * 수량
     * */
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;
    //Integer를 사용한 이유 : price, quantity가 null일 가능성도 있기 때문.

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
