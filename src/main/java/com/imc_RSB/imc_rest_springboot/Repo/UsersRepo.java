package com.imc_RSB.imc_rest_springboot.Repo;

import com.imc_RSB.imc_rest_springboot.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, Long> {
}
