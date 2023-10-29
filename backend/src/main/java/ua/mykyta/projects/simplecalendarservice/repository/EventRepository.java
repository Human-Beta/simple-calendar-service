package ua.mykyta.projects.simplecalendarservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.mykyta.projects.simplecalendarservice.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
