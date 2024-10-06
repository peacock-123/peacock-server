package com.peacock.core.domain.position

import com.peacock.core.domain.position.vo.PositionId
import org.springframework.data.repository.ListCrudRepository

interface PositionRepository : ListCrudRepository<Position, PositionId> {
    fun findByOrderByIdAsc(): List<Position>
}
