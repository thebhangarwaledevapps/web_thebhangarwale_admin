package com.app.admin.controller;

import com.app.admin.result.ClientError;
import com.app.admin.result.Result;
import com.app.admin.result.ServerError;
import com.app.admin.result.Success;
import com.app.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/getBhangarwaleFacebookFeed")
    public ResponseEntity<Result> getBhangarwaleFacebookFeed(
            @RequestParam(required = false) String nextPageAccessToken
    ) {
        return getResultResponseEntity(adminService.getBhangarwaleFacebookFeed(
                nextPageAccessToken
        ));
    }

    @GetMapping("/saveBhangarTypeAndPrice")
    public ResponseEntity<Result> saveBhangarTypeAndPrice(
            @RequestParam String bhangarType,
            @RequestParam String bhangarUnit,
            @RequestParam Double bhangarPrice
    ) {
        return getResultResponseEntity(adminService.saveBhangarTypeAndPrice(
                bhangarType,
                bhangarUnit,
                bhangarPrice
        ));
    }

    @GetMapping("/getBhangarList")
    public ResponseEntity<Result> getBhangarList() {
        return getResultResponseEntity(adminService.getBhangarList());
    }

    @GetMapping("/getBhangarItemInfo")
    public ResponseEntity<Result> getBhangarItemInfo(
            @RequestParam Long itemId
    ) {
        return getResultResponseEntity(
                adminService.getBhangarItemInfo(
                        itemId
                )
        );
    }

    @GetMapping("/validatedAdminId")
    public ResponseEntity validatedCustomerId(
            @RequestParam String adminId
    ) {
        return ResponseEntity.ok(adminService.validatedAdminId(adminId));
    }

    @GetMapping("/updateBhangarTypeAndPrice")
    public ResponseEntity<Result> updateBhangarTypeAndPrice(
            @RequestParam Long bhangarId,
            @RequestParam String bhangarType,
            @RequestParam String bhangarUnit,
            @RequestParam Double bhangarPrice
    ) {
        return getResultResponseEntity(adminService.updateBhangarTypeAndPrice(
                bhangarId,
                bhangarType,
                bhangarUnit,
                bhangarPrice
        ));
    }

    @GetMapping("/deleteBhangarTypeAndPrice")
    public ResponseEntity<Result> deleteBhangarTypeAndPrice(
            @RequestParam Long bhangarId
    ) {
        return getResultResponseEntity(adminService.deleteBhangarTypeAndPrice(
                bhangarId
        ));
    }

    private ResponseEntity getResultResponseEntity(Result result) {
        ResponseEntity responseEntity = null;
        if (result instanceof Success) {
            Success success = (Success) result;
            responseEntity = ResponseEntity.ok(success.getData());
        } else if (result instanceof ClientError) {
            ClientError clientError = (ClientError) result;
            responseEntity = ResponseEntity.badRequest().body(clientError.getException().getMessage());
        } else if (result instanceof ServerError) {
            ServerError serverError = (ServerError) result;
            responseEntity = ResponseEntity.internalServerError().body(serverError.getException().getMessage());
        }
        return responseEntity;
    }

}
