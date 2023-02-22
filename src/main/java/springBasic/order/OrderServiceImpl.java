package springBasic.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springBasic.discount.DiscountPolicy;
import springBasic.discount.FixDiscountPolicy;
import springBasic.discount.RateDiscountPolicy;
import springBasic.member.Member;
import springBasic.member.MemberRepository;
import springBasic.member.MemoryMemberRepository;

@Component
public class OrderServiceImpl implements OrderService {


    //실제적인건 추상, 구체 다 의존하는 관계가 된다 => 결과적으로 new 선언할 때 객체를 바꿔야한다
    //생각하면 OrderServiceImpl은 주입만 받아야하는데, 구현체까지 선택하게 되는 것이다. => 그래서 분리해야한다
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 일단 추상 클래스에 의존하게 만들었지만, NPE가 뜨기 때문에 주입을 해주어야한다

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //OrderServiceImpl 입장에서는 어떤 구현체가 들어오는지 모른다 => DIP 원칙 지킴
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

}
