package org.Java8;

import java.util.List;

public class Student{
	private String name;
	private int age;
	private Addressaas address;
	private List<MobileNumber> mobileNumbers;

	public Student(String name, int age, Addressaas address, List<MobileNumber> mobileNumbers) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.mobileNumbers = mobileNumbers;
	}
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public Addressaas getAddress() {
		return address;
	}

	public List<MobileNumber> getMobileNumbers() {
		return mobileNumbers;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setAddress(Addressaas address) {
		this.address = address;
	}

	public void setMobileNumbers(List<MobileNumber> mobileNumbers) {
		this.mobileNumbers = mobileNumbers;
	}

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name +
				", age=" + age +
				", address=" + address.getZipcode() +
				", mobileNumbers=" + mobileNumbers.get(0).getNumber() +
				'}';
	}
}