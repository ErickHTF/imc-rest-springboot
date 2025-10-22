package com.imc_RSB.imc_rest_springboot.Controller;

import com.imc_RSB.imc_rest_springboot.DTO.ImcRequest;
import com.imc_RSB.imc_rest_springboot.Models.Users;
import com.imc_RSB.imc_rest_springboot.Repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.imc_RSB.imc_rest_springboot.Service.ImcService;

@RestController
public class ApiController {

    @Autowired
    private UsersRepo usersRepo;

    @GetMapping(value = "/")
    public String getPage(){
        return "Hello World";
    }

    @GetMapping(value = "/users")
    public List<Users> getUsersRepo() {
        return usersRepo.findAll();
    }

    @PostMapping(value = "/save")
    public  String saveUsers(@RequestBody Users users){
        usersRepo.save(users);
        return "User Saved";
    }

    @PutMapping(value = "update/{id}")
    public String updateUsers( @PathVariable Long id, @RequestBody Users user){
        Users updateUser = usersRepo.findById(id).get();
        updateUser.setName(user.getName());
        updateUser.setAge(user.getAge());
        updateUser.setWeight(user.getWeight());
        updateUser.setHeight(user.getHeight());

        usersRepo.save(updateUser);

        return "User Updated";
    }

    @DeleteMapping(value = "delete/{id}")
    public String deleteUsers(@PathVariable Long id){
        Users deleteUser = usersRepo.findById(id).get();
        usersRepo.delete(deleteUser);


        return "User ID Deleted: " +id;
    }

    @Autowired
    private ImcService ImcService;

    @PostMapping(value = "/calculateImc")
    public String calculateImc(
            @RequestBody ImcRequest request)
    {
        double imc = ImcService.calculateImc(request.getWeight(), request.getHeight());
        String classification = ImcService.classifyImc(imc);

        String formattedImc = String.format("%.2f", imc);

        return "Your IMC is: " + formattedImc +
                ". Classification: " + classification;
    }
}
