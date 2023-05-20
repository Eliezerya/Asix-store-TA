package com.Platinum.Asixstore.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Apriori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String barang;

    private String rekomendasi;

    private int support;

}
