package com.example.repo;

import com.example.entity.data.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findAccountByUsername(String username);
    Account findAllByUsernameLike(String username);
    boolean existsByUsername(String username);

    @Modifying
    @Query("update Account set password = ?2 where id=?1")
    int updateUserPasswordById(int id,String newPassword);

    //原生sql
    @Modifying
    @Query(value = "update account set password = ?2 where username = ?1",nativeQuery = true)
    int updatePasswordByUsername(String username,String newPassword);
}
