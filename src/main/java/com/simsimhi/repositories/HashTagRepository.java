package com.simsimhi.repositories;

import com.simsimhi.entities.HashTag;
import com.simsimhi.entities.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<HashTag, String> {
}