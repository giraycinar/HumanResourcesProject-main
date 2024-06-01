package org.example.config.security;

import org.example.entity.Admin;
import org.example.repository.AdminRepository;
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
    public AdminRepository adminRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    public UserDetails getUserByAuthId(Long authId) {
        Optional<Admin> authUser=adminRepository.findOptionalById(authId);
        if (authUser.isEmpty()) {
            return null;
        }
        List<GrantedAuthority> yetkiListesi=new ArrayList<>();
        yetkiListesi.add(new SimpleGrantedAuthority("ADMIN"));
        yetkiListesi.add(new SimpleGrantedAuthority("MANAGER"));
        yetkiListesi.add(new SimpleGrantedAuthority("EMPLOYEE"));

        return org.springframework.security.core.userdetails.User.builder()
               // .username(authUser.get().getUserName())
                .password("")
                .accountLocked(false)
                .accountExpired(false)
                .authorities(yetkiListesi) //kullanıcını yetkilerini yazıyoruz
                .build();
    }
}

 */
