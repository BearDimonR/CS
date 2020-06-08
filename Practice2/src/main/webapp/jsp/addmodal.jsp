<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 2019-06-25
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="AddModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addModalLabel">Add goods group</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="" method="post">
                    Name<br>
                    <input type="text" name="name" class="mb-3">
                    <input type="submit" value="Add" class="page-link text-right">
                    <input type="hidden" name="action" value="add">
                </form>
            </div>

        </div>
    </div>
</div>