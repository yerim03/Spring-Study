package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order CreateOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);    //주문 회원 정보 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); //할인금액
        /*
        * Order 서비스 입장에서는 할인에 대한 것은 전혀 관여하지 않고,
        * 할인에 대한 것은 discountPolicy 서비스에서 관리함
        * -> 단일 책임의 원칙을 지켜서 설계한 것
        * */

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
