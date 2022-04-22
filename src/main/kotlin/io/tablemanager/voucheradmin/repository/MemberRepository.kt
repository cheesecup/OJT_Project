package io.tablemanager.voucheradmin.repository;

import io.tablemanager.voucheradmin.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member?, Int?> {
}