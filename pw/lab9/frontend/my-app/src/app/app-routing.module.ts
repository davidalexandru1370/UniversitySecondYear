import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllDocumentsComponent } from './all-documents/all-documents.component';
import { LoginComponent } from './login/login.component';
import { UpdateDocumentComponent } from './update-document/update-document.component';
import { DeleteDocumentComponent } from './delete-document/delete-document.component';
import { AddDocumentComponent } from './add-document/add-document.component';

const routes: Routes = [
  {
    path: 'allDocuments',
    component: AllDocumentsComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'updateDocument',
    component: UpdateDocumentComponent,
  },
  {
    path: 'deleteDocument',
    component: DeleteDocumentComponent,
  },
  {
    path: 'addDocument',
    component: AddDocumentComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
