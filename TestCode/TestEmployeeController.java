package com.employeeManagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.model.Employee;

public class TestEmployeeController {
   @Override
   @Before
   public void setUp() {
      super.setUp();
   }
   @Test
   public void TestGetEmployees() throws Exception {
      String uri = "/employees";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Employee[] employeelist = super.mapFromJson(content, Employee[].class);
      assertTrue(employeelist.length > 0);
   }
   @Test
   public void TestCreateEmployee() throws Exception {
      String uri = "/employees";
      Employee employee = new Employee();
      Employee.setId("3");
       Employee.setName("Ginger");
      String inputJson = super.mapToJson( employee);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(201, status);
      String content = mvcResult.getResponse().getContentAsString();
      assertEquals(content, "Employee is created successfully");
   }
   @Test
   public void TestUpdate Employee() throws Exception {
      String uri = "/employees/2";
       Employee  employee = new  Employee();
       employee.setName("Lemon");
      String inputJson = super.mapToJson( employee);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      assertEquals(content, " Employee is updated successsfully");
   }
   @Test
   public void TestDeleteEmployee() throws Exception {
      String uri = "/employees/2";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      assertEquals(content, " Employee is deleted successsfully");
   }
}