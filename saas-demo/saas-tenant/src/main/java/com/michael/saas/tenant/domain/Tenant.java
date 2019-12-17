package com.michael.saas.tenant.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tenant")
public class Tenant implements Serializable {

    @Id
    @Column(name = "id",length = 11)
    private Integer id;

    @Column(name = "account",length = 30)
    private String account;

    @Column(name = "token",length = 32)
    private String token;

    @Column(name = "url",length = 125)
    private String url;

    @Column(name = "data_base",length = 30)
    private String database;

    @Column(name = "username",length = 30)
    private String username;

    @Column(name = "password",length = 32)
    private String password;

//    @Column(name = "domain_name",length = 64)
//    private String domainName;
}
