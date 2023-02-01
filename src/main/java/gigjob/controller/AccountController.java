package gigjob.controller;

import gigjob.dto.AccountDTO;
import gigjob.entity.ResponseObject;
import gigjob.repository.AccountRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping(value = "/api/v1/account")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<ResponseObject> findAll() {
        List<AccountDTO> accountDTOS = accountRepository.findAll()
                .stream()
                .map(acc -> modelMapper.map(acc, AccountDTO.class)).toList();
        ResponseObject responseObject = new ResponseObject(HttpStatus.OK.toString(), "Get all successfully", accountDTOS);
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }

}
