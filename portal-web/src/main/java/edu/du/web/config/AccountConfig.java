package edu.du.web.config;

import edu.du.web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AccountConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                // 아까 만든 LoginCheckInterceptor 클래스 내용을 넘김
                .order(1)
                // order : 해당 인터셉터가 적용되는 순서, 1이면 첫번째로 실행
                .addPathPatterns("/account/**")
                // 현재 프로젝트의 모든 주소에 대해 인터셉터를 적용
                .excludePathPatterns( "/account", "/account/login","/account/signup","/account/loginId", "/css/**");
        // 그중에 이 주소는 제외
    }
}
