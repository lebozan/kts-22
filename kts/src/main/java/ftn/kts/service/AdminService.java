package ftn.kts.service;

import ftn.kts.model.Admin;
import ftn.kts.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }


    public Admin addNewAdmin(Admin admin) {
        return adminRepository.save(admin);
    }
}
