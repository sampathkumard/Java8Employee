package com.java8;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

class Employee {
	private int id;
	private String name;
	private int age;
	private String gender;
	private String department;
	private int yearOfJoining;
	private double salary;

	public Employee(int id, String name, int age, String gender, String department, int yearOfJoining, double salary) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.department = department;
		this.yearOfJoining = yearOfJoining;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getYearOfJoining() {
		return yearOfJoining;
	}

	public void setYearOfJoining(int yearOfJoining) {
		this.yearOfJoining = yearOfJoining;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", department="
				+ department + ", yearOfJoining=" + yearOfJoining + ", salary=" + salary + "]";
	}

}

public class Java8Employee {

	public static void main(String[] args) {

		List<Employee> employeeList = new ArrayList<Employee>();

		employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

		//number of male and female employees
		
		Map<String, Long> noOfMalesFemales=employeeList.stream().
				collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		
		System.out.println(noOfMalesFemales);

		//names of all departments

		employeeList.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);

		//avg age of males and females

		Map<String, Double> avgAges =employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
	
		System.out.println(avgAges);

		//details of highest paid employee

		Optional<Employee> highestPaidEmp=employeeList.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
		Employee highest = highestPaidEmp.get();
		System.out.println("Id is " + highest.getId());

		//names of empl joined after 2015
		employeeList.stream().filter(e->e.getYearOfJoining()>2015).map(Employee::getName).forEach(System.out::println);

		//no. of emp in each dept
		Map<String, Long> empByDepartment = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
			//System.out.println(empByDepartment);

			Set<Map.Entry<String, Long>> entrySet = empByDepartment.entrySet();

			for(Map.Entry<String,Long> entry : entrySet){
				System.out.println(entry.getKey() + "----" + entry.getValue());

			//avg salary of each dept
			Map<String, Double> avgSalaryByDept=employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
			
				Set<Map.Entry<String, Double>> entrySet1 = avgSalaryByDept.entrySet();
				for(Map.Entry<String,Double> entry1 : entrySet1){
					System.out.println(entry1.getKey() + "--" + entry1.getValue());

				}
				//details of youngest male employee in the product development department
				Optional<Employee> young= employeeList.stream().filter(e->e.getGender() =="Male" && e.getDepartment() =="Product Development")
				.min(Comparator.comparingInt(Employee::getAge));

				Employee youngMaleEmployee = young.get();
				System.out.println("Name ---" + youngMaleEmployee.getName());

				// most working experience employee in the organization

				Employee senior= employeeList.stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst().get();
				System.out.println("Senior emp name ---" + senior.getName());

				//number of male and female employees are there in the sales and marketing team

				Map<String,Long> malesFemales = employeeList.stream().filter(e->e.getDepartment() == "Sales And Marketing").
				collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));

				System.out.println(malesFemales);

				//average salary of male and female employees
				Map<String,Double> avgSalary=employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
				System.out.println(avgSalary);

				//List down the names of all employees in each department

				Map<String, List<Employee>> empListByDept= employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment));
				System.out.println(empListByDept);

				Set<Map.Entry<String,List<Employee>>> entrySet2 = empListByDept.entrySet();
				for(Map.Entry<String,List<Employee>> entry2 : entrySet2){
					System.out.println("Employees in " + entry2.getKey() + ":");
					System.out.println("---------------------");
					List<Employee> list = entry2.getValue();

					for(Employee e : list){
						System.out.println(e.getName());
					}
				}
				//average salary and total salary of the whole organization

				DoubleSummaryStatistics d =employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary ));
				System.out.println("Avg salary " + d.getAverage());
				System.out.println("Avg salary " + d.getSum());
			
			//Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years

			Map<Boolean, List<Employee>> sepByAge =employeeList.stream().collect(Collectors.partitioningBy(e->e.getAge()>25));
				Set<Map.Entry<Boolean, List<Employee>>> sepByAge1 = sepByAge.entrySet();

				for(Map.Entry<Boolean,List<Employee>> entry3:sepByAge1){
					if(entry3.getKey()){
						System.out.println("Above 25");
					}else{
						System.out.println("25 and below");
					}
					List<Employee> l = entry3.getValue();
					for(Employee e:l){
						System.out.println(e.getName());
					}
				}
			}

			//oldest employee in the organization? What is his age and which department he belongs to?

			Employee e=employeeList.stream().max(Comparator.comparing(Employee::getAge)).get();

			System.out.println(e.getName());
			System.out.println(e.getAge());
			System.out.println(e.getDepartment());
	}
}
