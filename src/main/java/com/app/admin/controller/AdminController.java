package com.app.admin.controller;

import com.app.admin.entity.BhangarTypeAndPrice;
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
    public ResponseEntity<BhangarTypeAndPrice> getBhangarItemInfo(
            @RequestParam Long itemId
    ) {
        return getMicroServiceResultResponseEntity(
                adminService.getBhangarItemInfo(
                        itemId
                )
        );
    }

    private ResponseEntity<Result> getResultResponseEntity(Result result) {
        if (result instanceof Success) {
            Success success = (Success) result;
            return ResponseEntity.ok(success);
        } else if (result instanceof ClientError) {
            ClientError clientError = (ClientError) result;
            return ResponseEntity.badRequest().body(clientError);
        } else if (result instanceof ServerError) {
            ServerError serverError = (ServerError) result;
            return ResponseEntity.internalServerError().body(serverError);
        } else return null;
    }

    private ResponseEntity getMicroServiceResultResponseEntity(Result result) {
        if (result instanceof Success) {
            Success success = (Success) result;
            return ResponseEntity.ok(success.getData());
        } else if (result instanceof ClientError) {
            ClientError clientError = (ClientError) result;
            return ResponseEntity.badRequest().body(clientError.getErrorMessage());
        } else if (result instanceof ServerError) {
            ServerError serverError = (ServerError) result;
            return ResponseEntity.internalServerError().body(serverError.getErrorMessage());
        } else return null;
    }

}
