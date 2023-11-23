<script setup></script>

<template>
  <section class="bg-light p-3 p-md-4 p-xl-5">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-12 col-xxl-11">
          <div class="card border-light-subtle shadow-sm">
            <div class="row g-0">
              <div class="col-12 col-md-6">
                <img
                  class="img-fluid rounded-start w-100 h-100 object-fit-cover"
                  loading="lazy"
                  src="https://images.unsplash.com/photo-1500835556837-99ac94a94552?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                  alt=""
                />
              </div>
              <div class="col-12 col-md-6 d-flex align-items-center justify-content-center">
                <div class="col-12 col-lg-11 col-xl-10">
                  <div class="card-body p-3 p-md-4 p-xl-5">
                    <div class="row">
                      <div class="col-12">
                        <div class="mb-5">
                          <div class="text-center mb-4">
                            <a href="#!">
                              <!-- <img src="./assets/img/bsb-logo.svg" alt="" width="175" height="57" /> -->
                            </a>
                          </div>
                          <h1 class="fw-light d-flex justify-content-center mt-5">로그인</h1>
                        </div>
                      </div>
                    </div>
                    <form action="#!">
                      <div class="row gy-3 overflow-hidden">
                        <div class="col-12">
                          <div class="form-floating mb-3">
                            <input
                              type="email"
                              class="form-control"
                              name="email"
                              id="email"
                              placeholder="name@example.com"
                              required
                            />
                            <label for="email" class="form-label">아이디</label>
                          </div>
                        </div>
                        <div class="col-12">
                          <div class="form-floating mb-3">
                            <input
                              type="password"
                              class="form-control"
                              name="password"
                              id="password"
                              value=""
                              placeholder="Password"
                              required
                            />
                            <label for="password" class="form-label">비밀번호</label>
                          </div>
                        </div>

                        <div class="col-12">
                          <div class="d-grid">
                            <button class="btn btn-dark btn-lg" type="submit">로그인</button>
                          </div>
                        </div>
                      </div>
                    </form>
                    <div class="row">
                      <div class="col-12">
                        <div
                          class="d-flex gap-2 gap-md-4 flex-column flex-md-row justify-content-md-center mt-5"
                        >
                          <a href="#!" class="link-secondary text-decoration-none">회원가입</a>
                          <a href="#!" class="link-secondary text-decoration-none">비밀번호 찾기</a>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped></style>
