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
                            <button name="subjectId" value="${subject.id}" class="btn btn-danger">delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        asdf
    </div>
</div>
<%@ include file="/includes/footer.jsp" %>