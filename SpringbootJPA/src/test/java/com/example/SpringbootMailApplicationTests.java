package com.example;

import com.example.entity.data.Account;
import com.example.entity.data.AccountDetail;
import com.example.repo.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootMailApplicationTests {
    @Resource
    AccountRepository repository;
    @Test
    void init(){

    }

    @Transactional   //懒加载属性需要在事务环境下获取，因为repository方法调用完后Session会立即关闭
    @Test
    @Commit
    void te() {
        repository.updatePasswordByUsername("Nike","654321");
    }

    @Test
    void contextLoads() {
        System.out.println(repository.findById(1));
    }

    @Test
    void addAccount(){
        Account account = new Account();
        account.setUsername("Nike");
        account.setPassword("123456");
        AccountDetail detail = new AccountDetail();
        detail.setAddress("重庆市渝中区解放碑");
        detail.setPhone("1234567890");
        detail.setEmail("73281937@qq.com");
        detail.setRealName("张三");
        account.setDetail(detail);
        account = repository.save(account);
        System.out.println("插入时，自动生成的主键ID为："+account.getId()+"，外键ID为："+account.getDetail().getId());
    }

    @Transactional
    @Test
    void deleteAccount(){
        repository.deleteById(3);   //根据ID删除对应记录
    }

    @Transactional
    @Test
    void pageAccount() {
        repository.findAll(PageRequest.of(0, 2)).forEach(System.out::println);  //直接分页
    }

    @Transactional
    @Test
    void test() {
        repository.findById(1).ifPresent(account -> {
            account.getScoreList().forEach(score -> {
                System.out.println("课程名称："+score.getSubject().getName());
                System.out.println("得分："+score.getScore());
                System.out.println("任课教师："+score.getSubject().getTeacher());
            });
        });
    }
}
