package com.iworks.reactspring.security.services;

import com.fiberhome.authservice.model.TokensEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRedisService extends JpaRepository<TokensEntity,UUID> {
    Optional<TokensEntity> findByAuthenticationToken(String jwt);
}
