package com.boot.spring.RetailBanking.Login.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.spring.RetailBanking.Login.Bean.Login;
@Repository
public interface LoginRepo extends JpaRepository<Login, String>{

}
