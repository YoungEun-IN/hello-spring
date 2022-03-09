package hello.spring;

import hello.spring.repository.MemberRepository;
import hello.spring.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
}