package ihundan.wukonglin.boot.config;

import ihundan.wukonglin.boot.security.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * spring security config
 */
@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter  {
    @Autowired
    private SecurityService securityService;

    /**
     * spring security http config
     * 配置权限，permitAll 静态资源；/admin/ 拥有ADMIN权限；/user/ 拥有USER权限
     * @param http HttpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/**", "/login**").permitAll()
//                .antMatchers("/admin/**").hasRole(SecurityRole.ADMIN.toString())
//                .antMatchers("/user/**").hasRole(SecurityRole.USER.name())
                .antMatchers("/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.jsp")//// default is /login with an HTTP get
                .loginProcessingUrl("/login")// default is /login with an HTTP post
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/login.jsp?error=1") // default is /login?error
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                /**
                 * 单点登陆，当在别的地方有登陆之后，原地址跳转到相应地址
                 */
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/login.jsp?error=1");
    }

    /**
     * auth config
     * 配置权限信息从数据库取
     * @param auth AuthenticationManagerBuilder
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityService);
    }

}
