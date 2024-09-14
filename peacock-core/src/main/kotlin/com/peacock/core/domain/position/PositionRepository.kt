package com.peacock.core.domain.position

import org.springframework.data.repository.ListCrudRepository

interface PositionRepository : ListCrudRepository<Position, Long>
