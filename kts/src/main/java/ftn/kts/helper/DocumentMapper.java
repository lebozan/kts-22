package ftn.kts.helper;

import ftn.kts.dtos.DocumentDTO;
import ftn.kts.model.Document;

public class DocumentMapper implements MapperInterface<Document, DocumentDTO> {
    @Override
    public Document toEntity(DocumentDTO dto) {
        Document d = new Document();
        d.setName(dto.getName());
        d.setImage(dto.getImage());
        return d;
    }

    @Override
    public DocumentDTO toDto(Document entity) {
        DocumentDTO dto = new DocumentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setDriverId(entity.getDriver().getId());

        return dto;
    }
}
