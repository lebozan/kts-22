package ftn.kts.controller;

import ftn.kts.dtos.*;
import ftn.kts.model.EmailTemplate;
import ftn.kts.model.Message;
import ftn.kts.model.Note;
import ftn.kts.model.User;
import ftn.kts.security.AuthTokenUtils;
import ftn.kts.service.MailService;
import ftn.kts.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;

    private final AuthTokenUtils tokenUtils;

    private final AuthenticationManager authenticationManager;

    private final MailService mailService;

    public UserController(UserService userService, AuthTokenUtils tokenUtils, AuthenticationManager authenticationManager, MailService mailService) {
        this.userService = userService;
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
        this.mailService = mailService;
    }


    @PutMapping(value = "/{id}/changePassword")
    public ResponseEntity<String> changePassword(@PathVariable Long id, @RequestBody PasswordChangeDTO dto) {
        userService.changePassword(id, dto);
        return new ResponseEntity<>("Password successfully changed!", HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}/resetPassword")
    public ResponseEntity<String> resetPassword(@PathVariable Long id) {
        UserDTO user = userService.getUserDtoById(id);
        EmailTemplate template = new EmailTemplate();
        template.setSendTo(user.getEmail());
        template.setSubject("Password reset code");
        template.setBody("Code for password reser: ");
        mailService.sendTextEmail(template);
        return new ResponseEntity<>("Email with reset code has been sent!", HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}/resetPassword")
    public ResponseEntity<String> changePasswordWithCode(@PathVariable Long id) {

        return new ResponseEntity<>("Password successfully changed!", HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}/rides")
    public ResponseEntity<?> getUserRides(@PathVariable Long id, @RequestParam int page, @RequestParam int size,
                                          @RequestParam String sort, @RequestParam Date from, @RequestParam Date to) {

        return null;
    }

    @GetMapping
    public ResponseEntity<Page<User>> getUsersPage(@RequestParam int page, @RequestParam int size) {

        return null;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthTokenDTO> login(@Valid @RequestBody LoginDTO loginDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = (User) authentication.getPrincipal();
            String jwt = tokenUtils.generateToken(user);
            int expiresIn = tokenUtils.getExpiredIn();
            return new ResponseEntity<>(new AuthTokenDTO(jwt, expiresIn), HttpStatus.OK);
        }

    }


    @GetMapping(value = "/{id}/messages")
    public  ResponseEntity<List<Message>> getAllUserMessages(@PathVariable Long id) {
        List<Message> messages = userService.getAllUserMessages(id);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/message")
    public ResponseEntity<Message> sendMessageToUser(@PathVariable Long id, @RequestBody MessageDTO messageDTO,
                                                     Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        Message newMessage = userService.sendMessageToUser(id, currentUser.getId(), messageDTO);


        return null;
    }

    @PutMapping(value = "/{id}/block")
    public ResponseEntity<String> blockUser(@PathVariable Long id) {
        userService.blockUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/unblock")
    public ResponseEntity<String> unblockUser(@PathVariable Long id) {
        userService.unblockUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/note")
    public ResponseEntity<Page<Note>> getUserNotes(@PathVariable Long id, @RequestParam int page, @RequestParam int size) {
        Page<Note> notesPage = userService.getNotesPageForUser(id, page, size);
        return null;
    }

    @PostMapping(value = "/{id}/note")
    public ResponseEntity<Note> createNote(@PathVariable Long id, @RequestBody NoteDTO noteDTO) {
        Note newNote = userService.createNoteForeUser(id, noteDTO);
        return new ResponseEntity<>(newNote, HttpStatus.OK);
    }




}
