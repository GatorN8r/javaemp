package controller;
import entities.EmployeeEntity;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import model.Employee;
import service.EmployeeEJB;

@ManagedBean(name = "employeecontroller")
@SessionScoped
public class EmployeeController{
 
    @EJB
    private EmployeeEJB employeeEJB;
    
  //from form
  	@ManagedProperty(value="#{employee}")
    private Employee employee;
  	
    private List<Employee> employeeList = new ArrayList<>();
  	
  	public EmployeeController() {
  		this.employeeList = getEmployeeList();
  	}
  	
   public List<Employee> getEmployeeList() {
	   
	   System.out.println("------ EmployeeController Loading Employees -------------");
	    employeeList = mapEmployees(employeeEJB.findEmployees());
	    System.out.println("EMPLOYEES " + employeeList.toString());
        return employeeList;
    }
 
   public String viewEmployee(){
        return "employeeList.xhtml";
    }
   
    public String addNewEmployee() {
 	   System.out.println("------ EmployeeController Adding Employee -------------");
         employeeEJB.addNew(employee.getEntity());
        
         
         employeeEJB.findEmployees();
         // employeeList = mapEmployees(employeeEJB.findEmployees());
        return "employeeList.xhtml";
    }

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	private List<Employee> mapEmployees(List<EmployeeEntity> employeeEntities) {
		 List<Employee> employees = new ArrayList<>();
		for(EmployeeEntity entity : employeeEntities) {
			Employee employee = new Employee();
			employee.setDateOfBirth(entity.getDateOfBirth());
			employee.setName(entity.getName());
			employee.setSurName(entity.getSurName());
			employees.add(employee);
		}
		return employees;
	}
    
    
}