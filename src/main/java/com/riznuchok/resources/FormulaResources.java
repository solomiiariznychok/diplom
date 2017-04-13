package com.riznuchok.resources;

import com.riznuchok.entity.Formula1;
import com.riznuchok.repository.FormulaRepository;
import com.riznuchok.service.FormulaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@RestController
@Path("/api/formula")
@Produces("application/json")
@PermitAll
@Slf4j
public class FormulaResources {

    @Autowired
    FormulaRepository formulaRepository;

    @Autowired
    FormulaService formulaService;

    @POST
    public Response calculate(Formula1 formula1){
        log.info("formula " + formula1);

        formula1 = formulaRepository.save((Formula1) formula1.calculate());
        return Response.ok(formula1).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") String id){
        return Response.ok(formulaRepository.findById(id)).build();
    }

    @GET
    public Response getAllFormualas(){
        return Response.ok(formulaRepository.findAll()).build();
    }




}
