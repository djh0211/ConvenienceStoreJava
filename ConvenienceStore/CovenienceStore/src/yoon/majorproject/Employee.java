package yoon.majorproject;

public class Employee {
    private EmployeeInfoData data;

    public EmployeeInfoData getData() {
        return data;
    }

    public void setData(EmployeeInfoData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }

    public Employee(EmployeeInfoData data) {
        this.data = data;
    }
}
