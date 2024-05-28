package myself.test.bookmanager.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import myself.test.bookmanager.controller.interceptor.CustomInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {//配置拦截器
        registry.addInterceptor(new CustomInterceptor())
                .addPathPatterns("/api/**") // 拦截所有api请求
                .excludePathPatterns("/api/user/login/**", "/api/user/register/**","/api/user/pwd"); // 排除登录和注册请求
    }

    //制作MP拦截器
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mpi=new MybatisPlusInterceptor();
        mpi.addInnerInterceptor(new PaginationInnerInterceptor());
        return mpi;
    }
    //配置Cors
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许所有路径
                .allowedOrigins("*") // 允许所有请求
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的方法
                .allowedHeaders("*") // 允许的头信息
                .allowCredentials(false) // 不允许携带认证信息（cookies, HTTP认证及客户端SSL证明等）
                .maxAge(3600); // 预检请求的缓存时间（秒），默认为1800秒
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }
}
