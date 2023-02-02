package ftn.kts.helper;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class RidePageImpl <Ride> extends PageImpl<Ride> {


    public RidePageImpl(List<Ride> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public RidePageImpl(List<Ride> content) {
        super(content);
    }
}
