<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>MyBatis</title>
</head>
<body>
<h1>MyBatis</h1>
<section>
    <form method="post" action="/students">
        <input name="name" placeholder="학생이름">
        <button>생성</button>
    </form>
</section>
<section>
    <form method="post" action="/courses">
        <input name="name" placeholder="과목이름">
        <button>생성</button>
    </form>
</section>
<section>
    <form method="post" action="/enrollments">
        <input name="studentId" type="number" placeholder="학생번호">
        <input name="courseId" type="number" placeholder="과목번호">
        <button>생성</button>
    </form>
</section>
<section>
    <c:forEach items="${students}" var="student">
        <p>${student}</p>
    </c:forEach>
</section>
<section>
    <c:forEach items="${courses}" var="course">
        <p>${course}</p>
    </c:forEach>
</section>
<section>
    <c:forEach items="${studentWithCourses}" var="student">
        <p>${student}</p>
    </c:forEach>
</section>
<section>
    <c:forEach items="${courseWithStudents}" var="course">
        <p>${course}</p>
    </c:forEach>
</section>
</body>
</html>
