package com.Platinum.Asixstore.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
@Getter
@Setter
public class RekomendasiUser {

    @Id
    int userId;

    String rekomendasi;

}
