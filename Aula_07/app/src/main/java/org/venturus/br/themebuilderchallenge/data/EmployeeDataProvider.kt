package org.venturus.br.themebuilderchallenge.data

import org.venturus.br.themebuilderchallenge.R

object EmployeeDataProvider {

    val allEmployees = listOf(
        Employee(
            id = 1,
            name = "John Doe",
            avatar = R.drawable.avatar_1,
            currentRole = "Software Engineer",
            salary = 8000,
            currentProject = "Project A",
            competences = listOf("Python", "JavaScript", "React")
        ),
        Employee(
            id = 2,
            name = "Jane Smith",
            avatar = R.drawable.avatar_2,
            currentRole = "Data Scientist",
            salary = 10000,
            currentProject = "Project B",
            competences = listOf("Python", "Data Analysis", "Machine Learning")
        ),
        Employee(
            id = 3,
            name = "Mike Johnson",
            avatar = R.drawable.avatar_3,
            currentRole = "DevOps Engineer",
            salary = 9000,
            currentProject = "Project C",
            competences = listOf("Docker", "Kubernetes", "CI/CD")
        ),
        Employee(
            id = 4,
            name = "Alice Lee",
            avatar = R.drawable.avatar_4,
            currentRole = "Android Developer",
            salary = 7000,
            currentProject = "Project D",
            competences = listOf("Kotlin", "Java", "Android SDK")
        ),
        Employee(
            id = 5,
            name = "Robert Chen",
            avatar = R.drawable.avatar_5,
            currentRole = "Frontend Developer",
            salary = 10000,
            currentProject = "Project E",
            competences = listOf("HTML", "CSS", "JavaScript")
        ),
        Employee(
            id = 6,
            name = "Emily Wang",
            avatar = R.drawable.avatar_6,
            currentRole = "UX Designer",
            salary = 7500,
            currentProject = "Project F",
            competences = listOf("User Research", "UI Design", "Prototyping")
        ),
        Employee(
            id = 7,
            name = "William Turner",
            avatar = R.drawable.avatar_7,
            currentRole = "Product Manager",
            salary = 13000,
            currentProject = "Project G",
            competences = listOf("Agile Methodology", "Product Strategy", "Market Analysis")
        ),
        Employee(
            id = 8,
            name = "Sophia Gonzalez",
            avatar = R.drawable.avatar_8,
            currentRole = "Backend Developer",
            salary = 11000,
            currentProject = "Project H",
            competences = listOf("Java", "Spring Boot", "RESTful APIs")
        ),
        Employee(
            id = 9,
            name = "Daniel Kim",
            avatar = R.drawable.avatar_9,
            currentRole = "Security Analyst",
            salary = 12000,
            currentProject = "Project I",
            competences = listOf("Penetration Testing", "Security Auditing", "Threat Analysis")
        ),
        Employee(
            id = 10,
            name = "Olivia Martinez",
            avatar = R.drawable.avatar_10,
            currentRole = "Data Engineer",
            salary = 9000,
            currentProject = "Project J",
            competences = listOf("SQL", "ETL", "Big Data")
        )
    )

}