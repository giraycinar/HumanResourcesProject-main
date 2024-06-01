package org.example.config.security;


import org.example.entity.Company;
import org.example.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/*
@Service
public class JwtUserDetail implements UserDetailsService {
    @Autowired
    public CompanyRepository companyRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    public UserDetails getUserById(Long id) {
        Optional<Company> user=companyRepository.findOptionalById(id);
        if (user.isEmpty()) {
            return null;
        }
        List<GrantedAuthority> yetkiListesi=new ArrayList<>();
        yetkiListesi.add(new SimpleGrantedAuthority("ADMIN"));
        yetkiListesi.add(new SimpleGrantedAuthority("MANAGER"));
        yetkiListesi.add(new SimpleGrantedAuthority("EMPLOYEE"));

        return org.springframework.security.core.userdetails.User.builder()
                //.username(authUser.get().getUserName())
                .password("")
                .accountLocked(false)
                .accountExpired(false)
                .authorities(yetkiListesi) //kullanıcını yetkilerini yazıyoruz
                .build();
    }
}

 */
