package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //생성자 주입
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

/*
이렇게 하면 DIP, OCP 위반
OrderServiceImpl가 DiscountPolicy(인터페이스)와 FixDiscountPolicy(구현체) 둘 다에 의존하고 있다. -> DIP 위반
FixDiscountPolicy 에서 RateDiscountPolicy로 변경 시 OrderServiceImpl의 코드도 함께 변경해야 한다 -> OCP 위반

//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);    //주문 회원 정보 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); //할인금액
        /*
        * Order 서비스 입장에서는 할인에 대한 것은 전혀 관여하지 않고,
        * 할인에 대한 것은 discountPolicy 서비스에서 관리함
        * -> 단일 책임의 원칙을 지켜서 설계한 것
        * */

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
