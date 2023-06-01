import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Document } from 'src/model/Document';
import { LoginCredentials } from 'src/model/LoginCredentials';

@Injectable({
  providedIn: 'root',
})
export class UserService {}
