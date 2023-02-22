package springBasic.discount;

import org.springframework.stereotype.Component;
import springBasic.member.Grade;
import springBasic.member.Member;

@Component
public class RateDiscountPolicy implements DiscountPolicy {
    private int discountPercent = 10; //10% 할인
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
