package ftn.kts.service;

import ftn.kts.dtos.MessageDTO;
import ftn.kts.dtos.NoteDTO;
import ftn.kts.dtos.PasswordChangeDTO;
import ftn.kts.dtos.UserDTO;
import ftn.kts.helper.UserMapper;
import ftn.kts.model.*;
import ftn.kts.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final PasswordResetRepository passwordResetRepository;
    private final MessageRepository messageRepository;
    private final NoteRepository noteRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                       PasswordResetRepository passwordResetRepository, MessageRepository messageRepository,
                       NoteRepository noteRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordResetRepository = passwordResetRepository;
        this.messageRepository = messageRepository;
        this.noteRepository = noteRepository;
        this.userMapper = new UserMapper();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmailAndActiveTrue(username).orElseThrow();
    }

    public Page<User> getUserPage(Pageable pageable) {
        return userRepository.findPageByActiveTrue(pageable);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllActiveUsers() {
        return userRepository.findAllByActiveTrue();
    }

    public List<User> getAllAdmins() {
        Role role = roleRepository.findByName("ROLE_ADMIN");
        return  userRepository.findByRoles_Id(role.getId());
    }

    public List<User> getAllDrivers() {
        Role role = roleRepository.findByName("ROLE_DRIVER");
        return  userRepository.findByRoles_Id(role.getId());
    }

    public List<User> getAllPassengers() {
        Role role = roleRepository.findByName("ROLE_PASSENGER");
        return  userRepository.findByRoles_IdAndActiveTrue(role.getId());
    }



    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }


    public boolean deleteUserById(Long id) {
        Optional<User> existing = userRepository.findById(id);
        if (existing.isEmpty()) {
            return false;
        }
        User u = existing.get();
        u.setActive(false);
        userRepository.save(u);
        return true;
    }

    public void trueDeleteById(Long id) {
        userRepository.deleteById(id);
    }


    public UserDTO addNewUser(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userMapper.toDto(userRepository.save(newUser));
    }


    public void changePassword(Long userId, PasswordChangeDTO dto) {
        Optional<User> userOptional = userRepository.findById(userId);
        userOptional.ifPresent(user -> {
            if (passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
                userRepository.save(user);
            }
        });
    }

    public UserDTO getUserDtoById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow());
    }

    public List<Message> getAllUserMessages(Long id) {
        return messageRepository.findAllByReceiverIdOrSenderId(id, id);
    }

    public void blockUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.ifPresent(user -> {
            user.setBlocked(true);
            userRepository.save(user);
        });
    }

    public void unblockUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.ifPresent(user -> {
            user.setBlocked(false);
            userRepository.save(user);
        });
    }

    public Note createNoteForeUser(Long userId, NoteDTO noteDTO) {
        Optional<User> userOptional = userRepository.findById(userId);
        AtomicReference<Note> n = new AtomicReference<>();
        userOptional.ifPresent(user -> {
            Note newNote = new Note();
            newNote.setMessage(noteDTO.getMessage());
            newNote.setUser(user);
            newNote = noteRepository.save(newNote);
            n.set(newNote);
        });

        return n.get();
    }

    public Page<Note> getNotesPageForUser(Long userId, int page, int size) {
        Page<Note> notes = noteRepository.findAllByUser_Id(userId, PageRequest.of(page, size));
        return notes;
    }

    public Message sendMessageToUser(Long userSentId, Long userReceivedId, MessageDTO messageDTO) {
        Message newMessage = new Message();
        newMessage.setReceiverId(userReceivedId);
        newMessage.setSenderId(userSentId);
        newMessage.setTimeOfSending(new Date());
        newMessage.setMessage(messageDTO.getMessage());
        newMessage.setMessageType(MessageType.valueOf(messageDTO.getMessageType()));
        return messageRepository.save(newMessage);
    }
}
