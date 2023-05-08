import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { DeleteDocumentComponent } from './delete-document/delete-document.component';
import { ShowDocumentsComponent } from './show-documents/show-documents.component';
import { UpdateDocumentComponent } from './update-document/update-document.component';
@NgModule({
  declarations: [AppComponent, DeleteDocumentComponent, ShowDocumentsComponent, UpdateDocumentComponent],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
