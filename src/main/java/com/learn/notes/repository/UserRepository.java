package com.learn.notes.repository;

import com.learn.notes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    @Query(value = "select * from tbl_user u where (u.email is not null and u.email=:email)" +
            " OR u.instituteid=:id " +
            " order by 1 desc limit 1", nativeQuery = true)
    User findUserByEmailAndInstitutionalId(String email, String id);

    @Query(value = "select * from tbl_user u where (u.email is not null and u.email=:email)" +
            " order by 1 desc limit 1", nativeQuery = true)
    User findUserByEmail(String email);

}
