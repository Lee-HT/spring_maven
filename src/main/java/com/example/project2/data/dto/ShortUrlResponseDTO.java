package com.example.project2.data.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@RedisHash(value = "shortUrl",timeToLive = 60)
public class ShortUrlResponseDTO implements Serializable {

    private static final long serialVersionID = -21449034996507077L;

    @Id
    private String OrgUrl;

    private String shortUrl;

}
