package com.example.entity.data;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;

    @JoinColumn(name = "detail_id")
    @OneToOne(cascade = CascadeType.ALL)
    AccountDetail detail;

    @JoinColumn(name = "uid")//一对多
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    List<Score> scoreList;
}
