import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AllDocumentsComponent } from './all-documents/all-documents.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { UpdateDocumentComponent } from './update-document/update-document.component';
import { DeleteDocumentComponent } from './delete-document/delete-document.component';
import { AddDocumentComponent } from './add-document/add-document.component';
@NgModule({
  declarations: [AppComponent, AllDocumentsComponent, LoginComponent, UpdateDocumentComponent, DeleteDocumentComponent, AddDocumentComponent],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
