<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">

<jsp:include page="/view/common/head.jsp">
    <jsp:param name="title" value="회원가입" />
</jsp:include>

<body>
    <jsp:include page="/view/common/header.jsp"/>




    <div class="container">
        <div class="input-form-backgroud row">
            <div class="input-form col-md-12 mx-auto">
                <div class="notification">${requestScope.isRegistFailed ? "회원가입이 실패하였습니다. 아이디 혹은 비밀번호를 정확하게 입력해주세요." : ""}</div>
                <h4 class="mb-3"><b>회원 가입</b></h4>
                <hr>
                <br>
                <form class="validation-form" action="/login/Regist" method="post" novalidate>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="name">이름</label>
                            <input type="text" class="form-control" name="name" id="name" placeholder="이름을 입력해주세요" value=""
                                required>
                            <div class="invalid-feedback">
                                이름을 입력해주세요.
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="userId">아이디</label>
                            <input type="text" class="form-control" name="userId" id="userId" placeholder="아이디를 입력해주세요" value=""
                                required>
                            <div class="invalid-feedback">
                                아이디를 입력해주세요.
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="password1">비밀번호</label>
                            <input type="password" class="form-control" name="password" id="password1" placeholder="비밀번호를 입력해주세요"
                                value="" required>
                            <div class="invalid-feedback">
                                비밀번호를 입력해주세요.
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="password2">비밀번호 확인</label>
                            <input type="password" class="form-control" id="password2" placeholder="비밀번호를 한 번 더 입력해주세요"
                                value="" required>
                            <div class="invalid-feedback">
                                비밀번호를 입력해주세요.
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="email">이메일</label>
                        <input type="email" class="form-control" name="email" id="email" placeholder="Bootstrap@example.com"
                            required>
                        <div class="invalid-feedback">
                            이메일을 입력해주세요.
                        </div>
                    </div>

                    <hr class="mb-4">
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="custom-control-input" id="aggrement" required>
                        <label class="custom-control-label" for="aggrement">개인정보 수집 및 이용에 동의합니다.</label>
                    </div>
                    <div class="mb-4"></div>
                    <button class="btn btn-secondary btn-block" type="submit">가입 완료</button>
                </form>
            </div>
        </div>
        <div class="p-2">
            <div class="footer">
                <footer>
                    <span class="text-muted d-flex justify-content-center">Copyright &copy; 2024 Bootstrap board</span>
                </footer>
            </div>
        </div>
    </div>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            var password1 = document.getElementById("password1");
            var password2 = document.getElementById("password2");

            function validatePassword() {
                if (password1.value !== password2.value) {
                    password2.setCustomValidity("비밀번호가 일치하지 않습니다.");
                } else {
                    password2.setCustomValidity("");
                }
            }

            password1.addEventListener("input", validatePassword);
            password2.addEventListener("input", validatePassword);
        });


        window.addEventListener('load', () => {
            const forms = document.getElementsByClassName('validation-form');

            Array.prototype.filter.call(forms, (form) => {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }

                    form.classList.add('was-validated');
                }, false);
            });
        }, false);

        setTimeout(() => {
            document.querySelector(".notification").hidden = true;
        }, 2000);
    </script>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3b"></script>