package com.codegym.case_study_team_2_study.service.account;

import com.codegym.case_study_team_2_study.model.account.Account;
import com.codegym.case_study_team_2_study.model.user_role.UserRole;
import com.codegym.case_study_team_2_study.repository.account.IAccountRepository;
import com.codegym.case_study_team_2_study.repository.user_role.IUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IUserRoleRepository userRoleRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.accountRepository.findAccountByEmail(username);
        if (account==null){
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User " + username+ " was not found in the database");
        }
        System.out.println("Found User: " + account);

        List<UserRole> userRoles = this.userRoleRepository.findByAccount(account);
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (userRoles != null) {
            for (UserRole userRole : userRoles) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(userRole.getRole().getRoleName());
                grantList.add(authority);
            }
        }
        UserDetails userDetails = (UserDetails) new User(account.getEmail(), account.getPassword(), grantList);
        return userDetails;
    }
}
