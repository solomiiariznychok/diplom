package com.riznuchok.resources;

import com.riznuchok.entity.Formula;
import com.riznuchok.repository.FormulaRepository;
import com.riznuchok.service.FormulaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@Path("/api/formula")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
@Slf4j
@CrossOrigin
public class FormulaResources {


    @Autowired
    FormulaService formulaService;

    @Autowired
    FormulaRepository formulaRepository;

   @POST
    public Response calculate(Formula formula){
        log.info("formula: " + formula);
        return Response.ok(formulaService.createFormula(formula)).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") String id){
        return Response.ok(formulaRepository.findById(id)).build();
    }

    @GET
    public Response getAllFormulas(){
        return Response.ok(formulaRepository.findAll(new Sort(new Sort.Order(Sort.Direction.DESC, "createdDate")))).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteById(@PathParam("id") String id){
        formulaRepository.deleteFormulaById(id);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    public Response updateFormulaById(Formula formula, @PathParam(value = "id") String id){
        return Response.ok(formulaService.updateFormula(id, formula)).build();
    }


}
