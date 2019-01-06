package ru.mytranslate.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mytranslate.domain.SystemLog;

public interface SystemLogRepository extends CrudRepository<SystemLog, Long> {
}
