package sof03.project.projectship.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sof03.project.projectship.domain.User;
import sof03.project.projectship.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User curruser = repository.findByUsername(username);

        if (curruser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(
            curruser.getUsername(),
            curruser.getPasswordHash(),
            AuthorityUtils.createAuthorityList("ROLE_" + curruser.getRole())
        );
    }
}
