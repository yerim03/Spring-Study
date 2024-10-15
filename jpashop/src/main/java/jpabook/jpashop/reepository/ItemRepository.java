package jpabook.jpashop.reepository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    //상품 저장
    public void save(Item item) {
        if (item.getId() == null) { //id가 없으면 신규 상품 등록
            em.persist(item);
        } else {    //id가 d있으며 기존 상품 수정
            em.merge(item); //update와 비슷
        }
    }

    //상품 목록 조회
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
