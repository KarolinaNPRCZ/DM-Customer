package com.nprcz.dmcustomer;


import org.springframework.data.jpa.repository.JpaRepository;


interface UserRoleRepository extends JpaRepository<UserRole, Long> {
   UserRole getUserRoleByName(Role name);
}
