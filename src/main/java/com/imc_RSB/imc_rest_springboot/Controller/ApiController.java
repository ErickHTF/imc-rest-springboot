package com.imc_RSB.imc_rest_springboot.Controller;

import com.imc_RSB.imc_rest_springboot.DTO.ImcRequest;
import com.imc_RSB.imc_rest_springboot.Models.Users;
import com.imc_RSB.imc_rest_springboot.Repo.UsersRepo;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import com.imc_RSB.imc_rest_springboot.Exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.imc_RSB.imc_rest_springboot.Service.ImcService;

@RestController
@RequestMapping("/api/users")
public class ApiController {

    @Autowired
    private UsersRepo usersRepo;

    @Operation(summary = "Get All Users",
            description = "Retorna todos os usuários cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content (mediaType = "application/json",
                schema = @Schema(implementation = ApiController.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/all")
    public List<Users> getUsersRepo() {
        return usersRepo.findAll();
    }

    @Operation(summary = "Get Users by ID",
            description = "Retorna um usuário pelo ID.")
    @GetMapping(value = "/{id}")
    public Users getUsersById(@PathVariable Long id){
        return usersRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on: " + id));
    }

    @Operation(summary = "Create User",
            description = "Cria um novo usuário.")
    @PostMapping()
    public  String saveUsers(@RequestBody Users users){
        usersRepo.save(users);
        return "User Saved!";
    }

    @Operation(summary = "Update  User",
            description = "Atualiza os dados de um usuário.")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Users> updateUsers(@PathVariable Long id, @RequestBody Users user){
        Users updateUser = usersRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on: " + id));
        updateUser.setName(user.getName());
        updateUser.setAge(user.getAge());
        updateUser.setWeight(user.getWeight());
        updateUser.setHeight(user.getHeight());

        final Users updatedUser = usersRepo.save(updateUser);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Delete User",
            description = "Remove um novo usuário.")
    @DeleteMapping(value = "/{id}")
    public Map<String, Boolean> deleteUsers(@PathVariable Long id){
        Users deleteUser = usersRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on: " + id));
        usersRepo.delete(deleteUser);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @Autowired
    private ImcService ImcService;

    @Operation(summary = "Calculate BMI",
            description = "Calcula um IMC.")
    @PostMapping(value = "/calculateImc")
    public String calculateImc(
            @RequestBody ImcRequest request)
    {
        return ImcService.calculateImc(request.getWeight(), request.getHeight());
    }
}
