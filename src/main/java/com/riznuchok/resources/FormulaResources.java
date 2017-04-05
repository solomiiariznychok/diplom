package com.riznuchok.resources;

import com.riznuchok.entity.Formula;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@RestController
@Path("/api/formula")
@Produces("application/json")
public class FormulaResources {

    @GET
    public Response formula(){
        Formula formula = new Formula();
        formula.setFormula("formula");
        return Response.ok()
                .entity(formula).build();
    }

    @GET
    @Path("{text}")
    public Response formula(@PathParam("text") String text){
        Formula formula = new Formula();
        formula.setFormula(text);
        return Response.ok()
                .entity(formula).build();
    }


    @POST
    public Response formula(@RequestBody Formula formula){
        System.out.print("formula: " + formula);
        return Response.ok().build();
    }




}
