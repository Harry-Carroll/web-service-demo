package com.kainos.ea.resources;

import com.kainos.ea.Dao.EmployeeDao;
import com.kainos.ea.Models.Employee;
import com.kainos.ea.db.EmployeeDB;
import io.swagger.annotations.Api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/api")
@Api("Engineering Academy Dropwizard API")
public class WebService {

    @GET
    @Path("/print/{msg}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsg(@PathParam("msg") String message) {
        Message myMessage = new Message("Hello from a RESTful Web service: " + message);

        //Read emp ID from path
        //Get employee from DB
        //Map DB Fields to employee instance
        //Return employee in response body
         return Response
                 .status(Response.Status.FORBIDDEN)
                 .entity(myMessage)
                 .build(); //This returns 200 response code
        //return "Hello from a RESTful Web service: " + message;
    }


    @GET
    @Path("/employee/{val}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmpById(@PathParam("val") int id) throws SQLException {
        EmployeeDao dao = new EmployeeDao();
        Employee employee = dao.selectEmployeeByID(EmployeeDB.getConnection(), id);


        return Response
                .status(Response.Status.OK)
                .entity(employee) //output the object into json format
                .build();
    }



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmps() throws SQLException {
        EmployeeDao dao = new EmployeeDao();
        List<Employee> emps = dao.getEmp(EmployeeDB.getConnection());



        return Response
                .status(Response.Status.OK)
                .entity(emps) //output the object into json format
                .build();
    }

    @POST
    @Path("/print")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendMsg(Message message) {
        return Response
                .status(Response.Status.CREATED)
                .build();
        //return "Hello from a RESTful Web service: " + message.getText();
    }


    @POST
    @Path("/add-employee")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response CreateEmp(Employee employee) throws SQLException {

        EmployeeDao dao = new EmployeeDao();

        dao.insertToEmployee(employee, EmployeeDB.getConnection());
        return Response
                .status(Response.Status.CREATED)
                //.entity()
                .build();
        //return "Hello from a RESTful Web service: " + message.getText();
    }





}
