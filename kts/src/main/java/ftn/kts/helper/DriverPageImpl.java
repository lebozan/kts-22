package ftn.kts.helper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import ftn.kts.dtos.DriverDTO;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class DriverPageImpl<DriverDTO> extends PageImpl<DriverDTO> {


    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public DriverPageImpl(@JsonProperty("content") List<DriverDTO> content,
                        @JsonProperty("number") int page,
                        @JsonProperty("size") int size,
                        @JsonProperty("totalElements") Long totalElements,
                        @JsonProperty("pageable") JsonNode pageable,
                        @JsonProperty("last") boolean last,
                        @JsonProperty("totalPages") int totalPages,
                        @JsonProperty("sort") JsonNode sort,
                        @JsonProperty("first") boolean first,
                        @JsonProperty("numberOfElements") int numberOfElements) {
        super(content, (Pageable) PageRequest.of(page, size), totalElements);
    }

    public DriverPageImpl(List<DriverDTO> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public DriverPageImpl(List<DriverDTO> content) {
        super(content);
    }

    public DriverPageImpl() {
        super(new ArrayList<DriverDTO>());
    }
}
