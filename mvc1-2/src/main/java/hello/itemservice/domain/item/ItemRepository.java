package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    /*
    실무에서는 HashMap 사용 X -> 여러 쓰레드에서 공유해서 사용할 경우 문제발생
    -> ConcurrentHasMap 사용
    */
    private static final Map<Long, Item> store = new HashMap<>();   //static 사용
    private static long sequence = 0L;  //static 사용

    //상품 저장
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    //상품 조회
    public Item findById(Long id) {
        return store.get(id);
    }

    //전체 상품 조회
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    //상품 수정
    public void update(Long id, Item updateParam) {
        Item findItem = findById(id);   //수정하고자 하는 상품 찾기
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    //테스트에서 사용
    public void clearStore() {
        store.clear();
    }
}
