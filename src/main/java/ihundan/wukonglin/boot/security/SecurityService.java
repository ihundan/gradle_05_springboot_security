package ihundan.wukonglin.boot.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SecurityService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		SecurityUser securityUser = new SecurityUser(1L,"ihundan","123","USER");
		if(!username.equals(securityUser.getName())){
            throw new UsernameNotFoundException( "Oops!" );
        }
		User user = new User(securityUser.getName(), securityUser.getPassword(), 
				AuthorityUtils.createAuthorityList(securityUser.getRole()));
        return user;
    }
}
