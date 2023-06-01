import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { UserService } from 'src/api/user.service';
import { LoginCredentials } from 'src/model/LoginCredentials';
import { Token } from 'src/model/Token';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginError: string = '';
  constructor(
    private userService: UserService,
    private httpClient: HttpClient,
    private router: Router
  ) {}
  baseUrl = 'http://localhost:5000/api/';
  userController = 'user/';
  header = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
      'Access-Control-Allow-Origin': '*',
    }),
  };

  handleLogin(username: string, password: string) {
    this.loginError = '';
    this.httpClient
      .post(this.baseUrl + this.userController + 'login', {
        password: password,
        username: username,
      } as LoginCredentials)
      .pipe(
        catchError((error) => {
          if (typeof error.error === 'string') {
            this.loginError = error.error;
          }
          return throwError(() => error.error);
        })
      )
      .subscribe((response) => {
        localStorage.setItem('token', (response as Token).jwt);
        this.router.navigate(['allDocuments']);
      });
  }
}
