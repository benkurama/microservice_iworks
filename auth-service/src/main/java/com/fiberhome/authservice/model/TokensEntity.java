package com.fiberhome.authservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@RedisHash(value = "Tokens", timeToLive = 86400)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokensEntity {

    @Id
    private String id;
    private String username;
    @JsonIgnore
    private String authenticationToken;
    @ElementCollection
    private List<String> role;
    private String modifiedBy;
    private LocalDateTime modifiedOn;
    private String createdBy;
    private LocalDateTime createdOn;
}
