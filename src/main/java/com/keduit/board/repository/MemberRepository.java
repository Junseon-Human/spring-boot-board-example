package com.keduit.board.repository;

import com.keduit.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findById(String Id);

    Member findByName(String name);
}
