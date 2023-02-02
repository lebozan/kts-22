package ftn.kts.repository;

import ftn.kts.model.WorkingHours;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WorkingHoursRepository  extends JpaRepository<WorkingHours, Long> {
    List<WorkingHours> findAllByDriver_Id(Long id);

    Page<WorkingHours> findAllByDriver_IdAndStartTimeAfterAndEndTimeBefore(Long id, Date from, Date to, Pageable pageable);

}
