package ftn.kts.helper;

public interface MapperInterface<T, U> {

    T toEntity(U dto);

    U toDto(T entity);

}
