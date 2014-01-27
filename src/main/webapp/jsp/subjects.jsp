<%@ include file="/includes/header.jsp" %>

<div class="container" align="center">
    <c:if test="${user.role.roleName ne 'student'}">
        <form action="subjects/add" method="post">
            <input type="hidden" name="numOfPages" value="${numOfPages}">
            <fmt:message key="subjects.subjectNameHolder" var="subjectNameHolder"/>
            <input type="text" class="input-large" name="subjectName" placeholder="${subjectNameHolder}">
            <button type="submit" class="btn btn-info" id="add">
                <i class="icon-plus"></i><fmt:message key="buttons.add"/>
            </button>
        </form>
    </c:if>
    <div class="container" align="center">
        <table class="table table-hover" style="width: 500px;">
            <c:forEach var="subject" items="${subjectList}">
                <tr>
                    <td>${subject.name}</td>
                    <td><a href="quizzes?subject_id=${subject.id}" class="btn"><fmt:message
                            key="buttons.getQuizzes"/></a></td>
                    <c:if test="${user.role.roleName ne 'student'}">
                        <td>
                            <form action="subjects/delete" method="post">
                                <input type="hidden" name="currentPage" value="${currentPage}">
                                <button name="subjectId" value="${subject.id}" class="btn btn-danger"><i
                                        class="icon-remove"></i> <fmt:message key="buttons.delete"/>
                                </button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <div class="pagination">
            <ul>
                <c:if test="${currentPage != 1}">
                    <li><a href="subjects?page=${currentPage-1}">«</a></li>
                </c:if>
                <c:forEach begin="1" end="${numOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="active"><a href="#">${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="subjects?page=${i}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage lt numOfPages}">
                    <li><a href="subjects?page=${currentPage+1}">»</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<%@ include file="/includes/footer.jsp" %>