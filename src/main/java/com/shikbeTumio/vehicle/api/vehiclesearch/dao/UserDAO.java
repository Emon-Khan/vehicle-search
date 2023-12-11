package com.shikbeTumio.vehicle.api.vehiclesearch.dao;

import com.shikbeTumio.vehicle.api.vehiclesearch.entity.Account;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserDAO extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
}
