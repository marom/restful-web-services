package com.marom.restfulwebserivces.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    //name: uri versioning
    @GetMapping("/v1/person")
    public PersonV1 personV1() {
        return new PersonV1("PersonV1 Name");
    }

    @GetMapping("/v2/person")
    public PersonV2 personV2() {
        return new PersonV2(new Name("PersonV2", "NameV2"));
    }

    //name: request parameter versioning
    //localhost:8080/person/param?version=1
    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 paramV1() {
        return new PersonV1("PersonV1 Name");
    }

    //localhost:8080/person/param?version=2
    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 paramV2() {
        return new PersonV2(new Name("PersonV2", "NameV2"));
    }

    // name: header versioning
    //localhost:8080/person/header
    // and add header param: X-API-VERSION value:1
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 headerV1() {
        return new PersonV1("PersonV1 Name");
    }

    //localhost:8080/person/header
    // and add header param: X-API-VERSION value:2
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 headerV2() {
        return new PersonV2(new Name("PersonV2", "NameV2"));
    }

    // name: accept header versioning or mime type versioning
    //localhost:8080/person/produces
    // and add Accept header param: application/my.company.app-v1+json
    @GetMapping(value = "/person/produces", produces = "application/my.company.app-v1+json")
    public PersonV1 producesV1() {
        return new PersonV1("PersonV1 Name");
    }

    //localhost:8080/person/produces
    // and add Accept header param: application/my.company.app-v2+json
    @GetMapping(value = "/person/produces", produces = "application/my.company.app-v2+json")
    public PersonV2 producesV2() {
        return new PersonV2(new Name("PersonV2", "NameV2"));
    }
}
