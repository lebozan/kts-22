package ftn.kts.service;


import ftn.kts.model.Authority;
import ftn.kts.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

    @Autowired
    public AuthorityRepository authorityRepository;


    public Authority getAuthorityByName(String name) {
        return authorityRepository.findByName(name);
    }
}
