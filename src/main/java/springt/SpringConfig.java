package springt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springt.repository.MemberRepository;
import springt.repository.MemoryMemberRepository;
import springt.service.MemberService;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
