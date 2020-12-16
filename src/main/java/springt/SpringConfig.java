package springt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springt.repository.JdbcMemberRepository;
import springt.repository.MemberRepository;
import springt.repository.MemoryMemberRepository;
import springt.service.MemberService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }

}
