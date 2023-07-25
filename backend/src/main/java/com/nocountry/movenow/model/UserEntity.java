package com.nocountry.movenow.model;

import com.nocountry.movenow.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@Entity
@SQLDelete(sql = "UPDATE user_ SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete = false")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String user_name;

    private String password;

    @Column(unique = true) // Asegura que el email sea único en la base de datos
    private String email; // Agrega el campo email

    @ElementCollection(targetClass = Role.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @Fetch(FetchMode.JOIN)
    private Set<Role> role;


    private String clientPhone;

    private String clientCUIT;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Moving> movings;

    @Column(name="soft_delete")
    private Boolean softDelete  = Boolean.FALSE;

    //--------------------- UserDetails props

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (Role r : role) {
            authorities.add(new SimpleGrantedAuthority(r.name()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



    //---------------------------- User Entity Props

    private String generateRandomNumber(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }

    // Method to generate a client phone number starting with '54' and followed by 9 random digits
    public void generateClientPhone() {
        String prefix = "54";
        String randomNumbers = generateRandomNumber(9);
        this.clientPhone = prefix + randomNumbers;
    }

    // Method to generate a client CUIT starting with '20-', followed by 8 random digits, and another random digit at the end
    public void generateClientCUIT() {
        String prefix = "20";
        String separator1 = "-";
        String separator2 = "-";
        String randomNumbers = generateRandomNumber(8);
        String lastDigit = generateRandomNumber(1);

        this.clientCUIT = prefix + separator1 + randomNumbers + separator2 + lastDigit;
    }


}
