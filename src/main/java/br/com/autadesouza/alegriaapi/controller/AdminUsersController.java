package br.com.autadesouza.alegriaapi.controller;

import br.com.autadesouza.alegriaapi.controller.response.MusicaResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/admin/users")
@AllArgsConstructor
public class AdminUsersController {

//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping()
//    public ResponseEntity<MusicaResponse> getAllUsers() {
//        List<MusicaResponse> musicas = MusicaResponse.fromDomain(musicasService.getMusicas());
//        return new ResponseEntity(musicas, OK);
//    }
}
