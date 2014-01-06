<%@ include file="/includes/header.jsp" %>

<div class="container">
    <div class="container" align="center">
        <table class="table table-hover" style="width: 500px;">
            <!--Here should be jstl forEach-->
            <c:forEach var="subject" items="${subjectList}">
                <tr>
                    <td>${subject.name}</td>
                    <td>${subject.id}</td>
                    <td>
                        <form action="subjects/delete" method="post">
                            <button name="subjectId" value="${subject.id}" class="btn btn-danger"><i class="icon-remove"></i> delete</button>
                        </form>
                    </td>
                    <td><a href="quizzes?subject_id=${subject.id}" class="btn">get quizzes</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<%@ include file="/includes/footer.jsp" %>